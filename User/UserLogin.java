package User;

public interface UserLogin {
    public boolean login(String userId, String password);

    public void passwordChange(String userId, String newPassword);
}