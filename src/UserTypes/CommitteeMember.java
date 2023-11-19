package UserTypes;

public class CommitteeMember extends Student{
    private int inChargeOf = -1;
    private int points;

    public CommitteeMember(Student oldState ,int campID){
        super(oldState.name, oldState.userID, oldState.faculty);
        this.inChargeOf = campID;
        points = 0;

    }

    public int getPoints(){
        return points;
    }

    public void addPoints(int numPoints){
        points += numPoints;
    }
    public void setInChargeCamp(int campID){
        this.inChargeOf = campID;
    }

    public int getInChargeCamp(){
        return inChargeOf;
    }
}
