package model;

/**
 * Users class Users.java
 */
/**
 *
 * @author Jedediah R Morgan
 */

public class Users {
    private int userId;
    private String userName;
    private String password;

    public Users(int userId, String userName, String password){
        this.userId = userId;
        this.userName = userName;
        this.password = password;
    }

    /**
     @return returns the user Id */
    public int getUserId(){return userId;}

    /**
     @return returns the user name */
    public String getUserName() {
        return userName;
    }

    /**
     @return returns the division Id */
    public String getPassword() {
        return password;
    }

    @Override
    /**
     * @return overrides the toString method and returns the user Id as a string */
    public String toString(){
        return String.valueOf(userId);
    }

}
