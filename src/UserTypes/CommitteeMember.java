package UserTypes;

public class CommitteeMember extends Student{
    private int campInCharge;
    private int points;

    public CommitteeMember(int campID){
        this.campInCharge = campID;
        points = 0;
    }

    public int getPoints(){
        return points;
    }

    public void addPoints(int numPoints){
        points += numPoints;
    }

    public int getInChargeCamp(){
        return campInCharge;
    }
}
