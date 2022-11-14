import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

public class Debate {
    private Date date;
    private String motion;
    private String motionType;
    private Boolean propWins;
    private Debater[] team1 = new Debater[4];
    public Debate(Date date) {
        this.date = date;
    }

    public String getDate(){
        return String.valueOf(date.getDate())+"/"+String.valueOf(date.getMonth()+1)+"/"+String.valueOf(date.getYear()+1900);
    }

    public String getMotion() {
        return motion;
    }

    public void setMotion(String motion) {
        this.motion = motion;
    }

    public String getMotionType() {
        return motionType;
    }

    public void setMotionType(String motion) {
        String[] words = motion.split(" ");
        motionType = words[2];
    }

    public Boolean getPropWins() {return propWins;}

    public void setPropWins(Boolean propWins) {
        this.propWins = propWins;
    }

    public Debater[] getTeam1() {
        return team1;
    }

    public void addTeam1(Debater debater, int role) {
        team1[role] = debater;
    }
}