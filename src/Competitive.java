import java.util.Date;

public class Competitive extends Debate{
    private boolean sykProp;
    private String enemy;

    public Competitive(Date date) {
        super(date);
    }

    public boolean getSykProp() {
        return sykProp;
    }

    public void setSykProp(boolean sykProp) {
        this.sykProp = sykProp;
    }

    public String getEnemy() {
        return enemy;
    }

    public void setEnemy(String enemy) {
        this.enemy = enemy;
    }
}
