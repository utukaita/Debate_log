
public class Practice extends Debate{
    private Debater[] team2 = new Debater[4];

    public Practice(int[] date) {
        super(date);
    }

    public Debater[] getTeam2() {
        return team2;
    }

    public void addTeam2(Debater debater, int role) {
        team2[role] = debater;
    }

}