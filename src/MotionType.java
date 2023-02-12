import java.util.ArrayList;

public class MotionType implements java.io.Serializable {
    private String name;
    private ArrayList<Practice> practices;
    private ArrayList<Competitive> competitives;

    public MotionType(String name) {
        this.name = name;
        practices = new ArrayList<>();
        competitives = new ArrayList<>();
    }

    public int getPracticePrevalence(){
        return practices.size();
    }

    public int getCompetitivePrevalence(){
        return competitives.size();
    }

    public double getCompetitiveWinRate(){
        int wins = 0;
        for (int i = 0; i < competitives.size(); i++) {
            if(competitives.get(i).getSykProp()==competitives.get(i).getPropWins())
                wins++;
        }
        if(getCompetitivePrevalence()==0) return -1;
        else return wins/getCompetitivePrevalence();
    }

    public ArrayList<Practice> getPractices() {
        return practices;
    }

    public void addPractice(Practice practice){
        practices.add(practice);
    }

    public void deletePractice(Practice practice){
        for (int i = 0; i < practices.size(); i++) {
            if(practice.equals(practices.get(i))){
                practices.remove(i);
                break;
            }
        }
    }

    public ArrayList<Competitive> getCompetitives() {
        return competitives;
    }

    public void addCompetitive(Competitive competitive){
        competitives.add(competitive);
    }

    public void deleteCompetitive(Competitive competitive){
        for (int i = 0; i < competitives.size(); i++) {
            if(competitive.equals(competitives.get(i))) {
                competitives.remove(i);
                break;
            }
        }
    }

    public String getName() {
        return name;
    }

}
