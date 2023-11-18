package User;

// Function: User class to implement all methods in the 3 interfaces

public class User extends AbstractUser {
    private String userId;
    private String password;
    private boolean userType;
    private String faculty;

    public User(String userId, String password) {
        super(userId, password);
    }

    public void setUserType(boolean isStaff) {
        userType = isStaff;
    }

    public boolean getUserType() {
        return userType;
    }

    public boolean login(String userId, String password) {
        if (this.userId.equals(userId) && this.password.equals(password)) {
            return true;
        }
        return false;
    }

    public void passwordChange(String userId, String newPassword) {
        if (this.userId.equals(userId)) {
            password = newPassword;
        }
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public String getFaculty(String userId) {
        if (this.userId.equals(userId)) {
            return this.faculty;
        }
        return null;
    }
}
