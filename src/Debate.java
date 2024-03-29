
public class Debate implements java.io.Serializable {
    private int[] date;
    private String motion;
    private MotionType motionType;
    private Boolean propWins;
    private Debater[] team1 = new Debater[4];

    public Debate(int[] date) {
        this.date = date;
    }

    public int[] getDate(){
        return date;
    }
    public String getDateString(){
        return date[0]+"/"+date[1]+"/"+date[2];
    }

    public String getMotion() {
        return motion;
    }

    public void setMotion(String motion) {
        this.motion = motion;
    }

    public MotionType getMotionType() {
        return motionType;
    }

    public void setMotionType(MotionType motionType) {
        this.motionType = motionType;
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