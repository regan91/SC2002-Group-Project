package User;

public class CommitteeMember extends Student {
    // private boolean isCommittee = true;
    private int points;
    private String isInChargeOf;

    public CommitteeMember(String userId, String password, String faculty) {
        super(userId, password, faculty);
    }

    public int getPoints() {
        return points;
    }

    public String getCampIC() {
        return isInChargeOf;
    }

}
