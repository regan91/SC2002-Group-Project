package UserTypes.Interfaces;

public interface IUserLogin {
    boolean login(String userID, String password);
    void passwordChange (String password);
}
