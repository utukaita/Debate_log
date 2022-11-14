import java.util.ArrayList;

public class Debater {
    private String name;
    private ArrayList<Practice> practices;
    private ArrayList<Competitive> competitives;

    public Debater(String name) {
        this.name = name;
        practices = new ArrayList<>();
        competitives = new ArrayList<>();
    }

    public double winRate(){
        return (practiceWinRate()*Double.valueOf(practices.size()) + competitiveWinRate()*Double.valueOf(competitives.size()))
                /Double.valueOf(practices.size()+competitives.size());
    }


    public double winRate(int role){
        return (practiceWinRate(role)*Double.valueOf(totalPracticeDebates(role)) + competitiveWinRate(role)*Double.valueOf(totalCompetitiveDebates(role)))
                /Double.valueOf(totalPracticeDebates(role)+totalCompetitiveDebates(role));
    }

    public double practiceWinRate(){
        int wins = 0;
        for (int i = 0; i < practices.size(); i++) {
            Practice p = practices.get(i);
            boolean added = false;
            int j = 0;
            while (j<4 && added == false){
                if(p.getTeam1()[j].equals(this)){
                    if(p.getPropWins()){
                        wins++;
                        added = true;
                    }
                }
                else if(p.getTeam2()[j].equals(this)){
                    if(!p.getPropWins()) {
                        wins++;
                        added = true;
                    }
                }
                j++;
            }
        }
        if(practices.size()==0)
            return 0;
        else
            return Double.valueOf(wins)/Double.valueOf(practices.size());
    }

    public double practiceWinRate(int role){
        int wins = 0;
        int total = 0;
        for (int i = 0; i < practices.size(); i++) {
            Practice p = practices.get(i);
            if(p.getTeam1()[role].equals(this)){
                total++;
                if(p.getPropWins())
                    wins++;
            }
            else if(p.getTeam2()[role].equals(this)){
                total++;
                if(!p.getPropWins())
                    wins++;
            }
        }
        if(total==0)
            return 0;
        else
            return Double.valueOf(wins)/Double.valueOf(total);
    }

    public int totalPracticeDebates(int role){
        int total = 0;
        for (int i = 0; i < practices.size(); i++) {
            Practice p = practices.get(i);
            if(p.getTeam1()[role].equals(this))
                total++;
            else if(p.getTeam2()[role].equals(this))
                total++;
        }
        return total;
    }

    public double competitiveWinRate(){
        int wins = 0;
        for (int i = 0; i < competitives.size(); i++) {
            Competitive c = competitives.get(i);
            boolean added = false;
            int j = 0;
            while (j<4 && added == false) {
                if (c.getTeam1()[j].equals(this)) {
                    if (c.getPropWins()) {
                        wins++;
                        added = true;
                    }
                }
                j++;
            }
        }
        if(competitives.size()==0)
            return 0;
        else
            return Double.valueOf(wins)/Double.valueOf(competitives.size());
    }

    public double competitiveWinRate(int role){
        int wins = 0;
        int total = 0;
        for (int i = 0; i < competitives.size(); i++) {
            Competitive c = competitives.get(i);
            if(c.getTeam1()[role].equals(this)) {
                total++;
                if (c.getPropWins() == c.getSykProp())
                    wins++;
            }
        }
        if(total==0)
            return 0;
        else
            return Double.valueOf(wins)/Double.valueOf(total);
    }

    public int totalCompetitiveDebates(int role){
        int total = 0;
        for (int i = 0; i < competitives.size(); i++) {
            Competitive c = competitives.get(i);
            if(c.getTeam1()[role].equals(this))
                total++;
        }
        return total;
    }

    public int getDebates(){
        return practices.size() + competitives.size();
    }

    public String getName() {
        return name;
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

}