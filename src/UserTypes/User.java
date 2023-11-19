package UserTypes;

import java.io.Serializable;

public class User extends AbstractUser implements Serializable {

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return this.name;
    }

    public String getUserID(){
        return userID;
    }
    public void setUserID(String userID){
        this.userID = userID;
    }
    public String getPassword(){
        // this SHOULD run only once!
        return this.password;
    }
    public void setPassword(String loadedPassword){
        // this SHOULD run only once!
        this.password = loadedPassword;
    }

    public void setUserType(boolean isStaff){
        this.userType = isStaff;
    }
    public boolean getUserType(){
        return userType;
    }
    public boolean login(String userId, String password){
        if ((this.password.contains(password)) && (this.userID.contains(userId))){
            return true;
        }
        return false;
    }
    public void passwordChange(String newPassword){
        this.password = newPassword;
    }
    public void setFaculty(String faculty){
        this.faculty = faculty;
    }
    public String getFaculty(){
        return faculty;
    }
}
