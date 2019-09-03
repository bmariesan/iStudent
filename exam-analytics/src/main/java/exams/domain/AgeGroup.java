package exams.domain;

/**
 * Created by Exerdath on 25-Jan-18.
 */
public class AgeGroup {
    public int from;
    public int to;
    public AgeGroup(int from, int to){
        this.from=from;
        this.to=to;
    }
    @Override
    public String toString(){
        return from+" - "+to;
    }
}
