import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

public class Practice extends Debate{
    private Debater[] team2 = new Debater[4];
    public Practice(Date date) {
        super(date);
        this.team2 = team2;
    }

    public Debater[] getTeam2() {
        return team2;
    }

    public void addTeam2(Debater debater, int role) {
        team2[role] = debater;
    }

}