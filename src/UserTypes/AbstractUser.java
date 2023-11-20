package UserTypes;

import UserTypes.Interfaces.IUserData;
import UserTypes.Interfaces.IUserFaculty;
import UserTypes.Interfaces.IUserLogin;
import UserTypes.Interfaces.IUserType;

public abstract class AbstractUser implements IUserType, IUserLogin, IUserFaculty, IUserData {
    protected String name = "";
    protected String userID = "";
    protected String password = "password";
    protected boolean userType = false;
    protected String faculty = "";

}
