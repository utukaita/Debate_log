import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Date;

public class Application {
    private ArrayList<Practice> practices;
    private ArrayList<Competitive> competitives;
    private ArrayList<Debater> debaters;
    private String fname;
    private GUI gui;

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
    public String getFname(){
        return fname;
    }

    public void setData() {
        File file = new File(fname);
        if (file.exists() && !file.isDirectory()) {
            try (ObjectInputStream in =
                         new ObjectInputStream(new FileInputStream(fname))) {
                practices = (ArrayList<Practice>) in.readObject();
                competitives = (ArrayList<Competitive>) in.readObject();
                debaters = (ArrayList<Debater>) in.readObject();
                System.out.println("hophup");
            } catch (Exception e) {
                System.out.println(e);
            }
        } else {
            practices = new ArrayList<>();
            competitives = new ArrayList<>();
            debaters = new ArrayList<>();
            System.out.println("hophap");
        }
    }
    public void addPractice(String motion, boolean propWins, String[] propNames, String[] oppNames) {
        Date date = new Date();
        Practice practice = new Practice(date);
        practice.setMotion(motion);
        practice.setMotionType(motion);
        practice.setPropWins(propWins);
        addPracticeDebaters(practice, true, propNames);
        addPracticeDebaters(practice, false, oppNames);
        practices.add(practice);
        System.out.println(practice);
    }

    public void addCompetitive(String motion, String enemy, boolean sykProp, boolean propWins, String[] names) {
        Date date = new Date();
        Competitive competitive = new Competitive(date);
        competitive.setMotion(motion);
        competitive.setMotionType(motion);
        competitive.setEnemy(enemy);
        competitive.setSykProp(sykProp);
        competitive.setPropWins(propWins);
        addCompetitiveDebaters(competitive, names);
        competitives.add(competitive);
        System.out.println(competitive);
    }

    public void deletePractice(int n){
        for (int i = 0; i < 4;i++) {
            practices.get(n).getTeam1()[i].deletePractice(practices.get(n));
            practices.get(n).getTeam2()[i].deletePractice(practices.get(n));
        }
        practices.remove(n);
    }

    public void deleteCompetitive(int n){
        for (int i = 0; i < 4; i++) {
            competitives.get(n).getTeam1()[i].deleteCompetitive(competitives.get(n));
        }
        competitives.remove(n);
    }

    public void addPracticeDebaters(Practice practice, boolean isTeam1, String[] names) {
        for (int i = 0; i < names.length; i++) {
            boolean newDebater = true;
            Debater debater = new Debater(names[i]);
            for (int j = 0; j < i; j++) {
                if(isTeam1) {
                    if (debater.getName().equals(practice.getTeam1()[j].getName()))
                        newDebater = false;
                }
                else {
                    if (debater.getName().equals(practice.getTeam2()[j].getName()))
                        newDebater = false;
                }
            }
            int n = addDebater(debater);
            if (n == -1) {
                if (isTeam1)
                    practice.addTeam1(debater, i);
                else
                    practice.addTeam2(debater, i);
                if(newDebater) debater.addPractice(practice);
            } else {
                if (isTeam1)
                    practice.addTeam1(debaters.get(n), i);
                else
                    practice.addTeam2(debaters.get(n), i);
                if(newDebater) debaters.get(n).addPractice(practice);
            }
        }
    }

    public void addCompetitiveDebaters(Competitive competitive, String[] names) {
        for (int i = 0; i < names.length; i++) {
            boolean newDebater = true;
            Debater debater = new Debater(names[i]);
            for (int j = 0; j < i; j++) {
                if (debater.getName().equals(competitive.getTeam1()[j].getName()))
                    newDebater = false;
            }
            int n = addDebater(debater);
            if (n == -1) {
                competitive.addTeam1(debater, i);
                if(newDebater) debater.addCompetitive(competitive);
            } else {
                competitive.addTeam1(debaters.get(n), i);
                if(newDebater) debaters.get(n).addCompetitive(competitive);
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



    public double practiceWinningSide(){
        double prop = 0;
        for (int i = 0; i < practices.size(); i++) {
            if (practices.get(i).getPropWins() == true) prop++;
        }
        if (practices.size()==0)
            return -1;
        else return prop/practices.size();
    }

    public double sykWinRate(){
        double syk = 0;
        for (int i = 0; i < competitives.size(); i++) {
            if(competitives.get(i).getSykProp()==competitives.get(i).getPropWins()) syk++;
        }
        if (competitives.size()==0)
            return -1;
        else
            return syk/competitives.size();
    }

    public double[] getCompetitiveMotionTypeSykRate(String[] motionTypes){
        double[] total = new double[motionTypes.length];
        double[] syk = new double[motionTypes.length];
        for (int i = 0; i < competitives.size(); i++) {
            for (int j = 0; j < motionTypes.length; j++) {
                if(competitives.get(i).getMotionType().equals(motionTypes[j])) {
                    total[j]++;
                    if (competitives.get(i).getSykProp() == competitives.get(i).getPropWins())
                        syk[j]++;
                }
            }
        }
        double[] output = new double[motionTypes.length];
        for (int i = 0; i < output.length; i++) {
            output[i] = syk[i]/total[i];
        }
        return output;
    }

    public String[] getPracticeMotionTypes(){
        ArrayList<String> types = new ArrayList<>();
        for (int i = 0; i < practices.size(); i++) {
            boolean newType = true;
            for (int j = 0; j < types.size(); j++) {
                if(practices.get(i).getMotionType().equals(types.get(j)))
                    newType = false;
            }
            if(newType) types.add(practices.get(i).getMotionType());
        }
        String[] output = new String[types.size()];
        output = types.toArray(output);
        return output;
    }

    public String[] getCompetitiveMotionTypes(){
        ArrayList<String> types = new ArrayList<>();
        for (int i = 0; i < competitives.size(); i++) {
            boolean newType = true;
            for (int j = 0; j < types.size(); j++) {
                if(competitives.get(i).getMotionType().equals(types.get(j)))
                    newType = false;
            }
            if(newType) types.add(competitives.get(i).getMotionType());
        }
        String[] output = new String[types.size()];
        output = types.toArray(output);
        return output;
    }

    public double[] getPracticeMotionTypeFrequency(String motionTypes[]){
        double[] output = new double[motionTypes.length];
        for (int i = 0; i < practices.size(); i++) {
            for (int j = 0; j < motionTypes.length; j++) {
                if(practices.get(i).getMotionType().equals(motionTypes[j]))
                    output[j]++;
            }
        }
        for (int i = 0; i < output.length; i++) {
            output[i] = output[i]/practices.size();
        }
        return output;
    }

    public GUI getGui() {
        return gui;
    }

    public void setGui(GUI gui) {
        this.gui = gui;
    }
}