package User;

// Interface realisation: Abstract class to consolidate all 3 interfaces

public abstract class AbstractUser implements UserType, UserFaculty, UserLogin {
    protected String userId;
    protected String password;
    protected boolean userType;
    protected String faculty;

    public AbstractUser(String userId, String password) {
        this.userId = userId;
        this.password = password;
    }

}
