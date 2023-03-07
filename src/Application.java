import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Application {
    private ArrayList<Practice> practices;
    private ArrayList<Competitive> competitives;
    private ArrayList<Debater> debaters;
    private ArrayList<MotionType> motionTypes;
    private String[] officialMotionTypeNames = {"would", "supports", "believes", "regrets", "prefers", "opposes"};

    private String fname;
    private GUI gui;
    private Boolean exception;

    public Application() {
        fname = "data.ser";
        setData();
        gui = new GUI(this);
    }

    public ArrayList<Practice> getPractices() {
        return practices;
    }
    public ArrayList<Competitive> getCompetitives() {
        return competitives;
    }
    public ArrayList<Debater> getDebaters() {
        return debaters;
    }
    public ArrayList<MotionType> getMotionTypes(){
        return motionTypes;
    }
    public String getFname(){
        return fname;
    }

    public void setData() {
        // Creating the abstract File object with the proper name "data.ser"
        File file = new File(fname);
        // Initialising the boolean variable exception
        exception = false;
        // Testing if a file corresponding to the object exists
        if (file.exists() && !file.isDirectory() ) {
            // Opening the serialization stream
            try (ObjectInputStream in =
                         new ObjectInputStream(new FileInputStream(fname))) {
                // Reading the data which is in form of 3 lists
                practices = (ArrayList<Practice>) in.readObject();
                competitives = (ArrayList<Competitive>) in.readObject();
                debaters = (ArrayList<Debater>) in.readObject();
                motionTypes = (ArrayList<MotionType>) in.readObject();
                // Catching possible errors
            } catch (Exception e) {
                System.out.println(e);
                exception = true;
            }
        } else {
            exception = true;
        }
        // In case of the file not existing or something going wrong, creating new empty lists
        if(exception){
            practices = new ArrayList<>();
            competitives = new ArrayList<>();
            debaters = new ArrayList<>();
            motionTypes = new ArrayList<>();
            // Adding all official motion types to the list
            for (int i = 0; i < officialMotionTypeNames.length; i++) {
                motionTypes.add(new MotionType(officialMotionTypeNames[i]));
            }
        }
    }

    public void addPractice(String motion, int[] date, boolean propWins, String[] propNames, String[] oppNames) {
        Practice practice = new Practice(date);
        practice.setMotion(motion);
        addPracticeMotionType(practice);
        practice.setPropWins(propWins);
        addPracticeDebaters(practice, true, propNames);
        addPracticeDebaters(practice, false, oppNames);
        for (int i = 0; i < practices.size(); i++) {
            if(isNewer(practice, practices.get(i))){
                practices.add(i,practice);
                return;}
        }
        practices.add(practice);
    }

    public void addCompetitive(String motion, int[] date, String enemy, boolean sykProp, boolean propWins, String[] names) {
        Competitive competitive = new Competitive(date);
        competitive.setMotion(motion);
        addCompetitiveMotionType(competitive);
        competitive.setEnemy(enemy);
        competitive.setSykProp(sykProp);
        competitive.setPropWins(propWins);
        addCompetitiveDebaters(competitive, names);
        for (int i = 0; i < competitives.size(); i++) {
            if(isNewer(competitive, competitives.get(i))){
                competitives.add(i,competitive);
                return;}
        }
        competitives.add(competitive);
    }

    public void deletePractice(int n){
        for (int i = 0; i < 4; i++) {
            practices.get(n).getTeam1()[i].deletePractice(practices.get(n));
            practices.get(n).getTeam2()[i].deletePractice(practices.get(n));
        }
        practices.get(n).getMotionType().deletePractice(practices.get(n));
        practices.remove(n);
    }

    public void deleteCompetitive(int n){
        for (int i = 0; i < 4; i++) {
            competitives.get(n).getTeam1()[i].deleteCompetitive(competitives.get(n));
        }
        competitives.get(n).getMotionType().deleteCompetitive(competitives.get(n));
        competitives.remove(n);
    }

    public void addPracticeDebaters(Practice practice, boolean isTeam1, String[] names) {
        for (int i = 0; i < names.length; i++) {
            Debater debater = new Debater(names[i]);
            int n = addDebater(debater);
            if (n == -1) {
                if (isTeam1)
                    practice.addTeam1(debater, i);
                else
                    practice.addTeam2(debater, i);
                debater.addPractice(practice);
            } else {
                Debater d = debaters.get(n);
                if (isTeam1)
                    practice.addTeam1(d, i);
                else
                    practice.addTeam2(d, i);
                if (d.getPractices().size()==0) d.addPractice(practice);
                else if (!d.getPractices().get(d.getPractices().size() - 1).equals(practice)) d.addPractice(practice);
            }
        }
    }

    // This method is called when a new competitive debate is added.
    // It both adds debaters under the new debate and the new debate under the debaters
    public void addCompetitiveDebaters(Competitive competitive, String[] names) {
        // The parameters are the new debate and the names of the debaters given by the user
        // Looping through each name
        for (int i = 0; i < names.length; i++) {
            // Creating the object
            Debater debater = new Debater(names[i]);
            // Adding it to the list of debaters, if it does not already appear
            // n is the index of the debater if it already appears in the list
            // otherwise n is -1
            int n = addDebater(debater);
            // n is the index of the debater if it already appears in the list
            // otherwise n is -1
            if (n == -1) {
                // adding the new debater to the debate
                competitive.addTeam1(debater, i);
                // adding the debate to the debater's list
                debater.addCompetitive(competitive);
            } else {
                Debater d = debaters.get(n);
                // adding the already existing debater to the debate
                competitive.addTeam1(d, i);
                // adding the debate to the debater's list if it has not already been added
                // first testing if there are any debates in the debater's list
                if (d.getCompetitives().size()==0) d.addCompetitive(competitive);
                // then if the last debate added is the debate in question (it must be last as only one debate is analyzed at a time)
                else if (!d.getCompetitives().get(d.getCompetitives().size() - 1).equals(competitive)) d.addCompetitive(competitive);
            }
        }
    }

    public int addDebater(Debater debater) {
        int mark = -1;
        boolean flag = true;
        for (int i = 0; i < debaters.size(); i++) {
            if (debaters.get(i).getName().equals(debater.getName())) {
                mark = i;
                flag = false;
                break;
            }
        }
        if (flag) debaters.add(debater);
        return mark;
    }

    public void deleteDebater(int n){
        debaters.remove(n);
    }

    public void addPracticeMotionType(Practice practice){
        String[] words = practice.getMotion().split(" ");
        String name = words[2].replaceAll("\\p{Punct}","");
        for (int i = 0; i < motionTypes.size(); i++) {
            if(name.equals(motionTypes.get(i).getName())) {
                practice.setMotionType(motionTypes.get(i));
                motionTypes.get(i).addPractice(practice);
                return;
            }
        }
            MotionType motionType = new MotionType(name);
            practice.setMotionType(motionType);
            motionType.addPractice(practice);
    }

    public void addCompetitiveMotionType(Competitive competitive){
        String[] words = competitive.getMotion().split(" ");
        String name = words[2].replaceAll("\\p{Punct}","");
        for (int i = 0; i < motionTypes.size(); i++) {
            if(name.equals(motionTypes.get(i).getName())) {
                competitive.setMotionType(motionTypes.get(i));
                motionTypes.get(i).addCompetitive(competitive);
                return;
            }
        }
        MotionType motionType = new MotionType(name);
        competitive.setMotionType(motionType);
        motionType.addCompetitive(competitive);
    }

    public boolean isNewer(Debate debate1, Debate debate2){
        int[] parts1 = debate1.getDate();
        int[] parts2 = debate2.getDate();
        for (int i = 2; i >= 0; i--) {
            if(parts1[i]!=parts2[i]){
                if(parts1[i]>parts2[i])
                    return true;
                else
                    return false;
            }
        }
        return true;

    }

    public void sortDebaters(){
        Collections.sort(debaters, Comparator.comparingDouble(Debater::winRate));
        Collections.reverse(debaters);
    }

    public double practiceWinningSide(){
        double prop = 0;
        for (int i = 0; i < practices.size(); i++) {
            if (practices.get(i).getPropWins() == true) prop++;
        }
        if (practices.size()==0)
            return 0;
        else return prop/practices.size();
    }

    public double sykWinRate(){
        double syk = 0;
        for (int i = 0; i < competitives.size(); i++) {
            if(competitives.get(i).getSykProp()==competitives.get(i).getPropWins()) syk++;
        }
        if (competitives.size()==0)
            return 0;
        else
            return syk/competitives.size();
    }

    public ArrayList<MotionType> getPracticeMotionTypes(){
        ArrayList<MotionType> practiceTypes = new ArrayList<>();
        for (int i = 0; i < motionTypes.size(); i++) {
            if(motionTypes.get(i).getPracticePrevalence()>0)
                practiceTypes.add(motionTypes.get(i));
        }
        return practiceTypes;
    }

    public ArrayList<MotionType> getCompetitiveMotionTypes(){
        ArrayList<MotionType> competitiveTypes = new ArrayList<>();
        for (int i = 0; i < motionTypes.size(); i++) {
            if(motionTypes.get(i).getCompetitivePrevalence()>0)
                competitiveTypes.add(motionTypes.get(i));
        }
        return competitiveTypes;
    }

    public double[] getPracticeMotionTypeFrequency(){
        double[] output = new double[getPracticeMotionTypes().size()];
        for (int i = 0; i < output.length; i++) {
            output[i] = Double.valueOf(getPracticeMotionTypes().get(i).getPracticePrevalence())/Double.valueOf(practices.size());
        }
        return output;
    }

    public double[] getCompetitiveMotionTypeSykRate(){
        double[] output = new double[getCompetitiveMotionTypes().size()];
        for (int i = 0; i < output.length; i++) {
            output[i] = getCompetitiveMotionTypes().get(i).getCompetitiveWinRate();
        }
        return output;
    }

    public String[] getOfficialMotionTypes(){
        return officialMotionTypeNames;
    }

    public String[] suggestedTypes() {
        ArrayList<MotionType> officialMotionTypes = new ArrayList<>();
        for (int i = 0; i < motionTypes.size(); i++) {
            for (int j = 0; j < officialMotionTypeNames.length; j++) {
                if(motionTypes.get(i).getName().equals(officialMotionTypeNames[j]))
                    officialMotionTypes.add(motionTypes.get(i));
            }
        }
        Collections.sort(officialMotionTypes, Comparator.comparingInt(MotionType::getPracticePrevalence));
        for (int i = 1; i < officialMotionTypes.size(); i++) {
            int backwards = 1;
            while(backwards<=i) {
                if (officialMotionTypes.get(i).getPracticePrevalence() == officialMotionTypes.get(i-backwards).getPracticePrevalence()) {
                    if (officialMotionTypes.get(i-backwards+1).getCompetitiveWinRate() <
                            officialMotionTypes.get(i-backwards).getCompetitiveWinRate()) {
                        MotionType reserve = officialMotionTypes.get(i-backwards+1);
                        officialMotionTypes.set(i-backwards+1, officialMotionTypes.get(i - backwards));
                        officialMotionTypes.set(i-backwards, reserve);
                    }
                    backwards++;
                }
                else backwards += officialMotionTypes.size();
            }
        }
        String[] output = new String[officialMotionTypes.size()];
        for (int i = 0; i < output.length; i++) {
            output[i]=officialMotionTypes.get(i).getName();
        }
        return output;
    }
}