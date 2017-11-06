package pe.com.push_tdp.push_tdp.models;

/**
 * Created by Angelo-pooh on 30/10/2017.
 */

public class User {

    private int id;
    private String codeUser;
    private String firstNameUser;
    private String lastNameUser;
    private String passwordUser;

    public User(int id, String codeUser, String firstNameUser, String lastNameUser, String passwordUser) {
        this.id = id;
        this.codeUser = codeUser;
        this.firstNameUser = firstNameUser;
        this.lastNameUser = lastNameUser;
        this.passwordUser = passwordUser;
    }

    public User() {
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCodeUser() {
        return codeUser;
    }

    public void setCodeUser(String codeUser) {
        this.codeUser = codeUser;
    }

    public String getFirstNameUser() {
        return firstNameUser;
    }

    public void setFirstNameUser(String firstNameUser) {
        this.firstNameUser = firstNameUser;
    }

    public String getLastNameUser() {
        return lastNameUser;
    }

    public void setLastNameUser(String lastNameUser) {
        this.lastNameUser = lastNameUser;
    }

    public String getPasswordUser() {
        return passwordUser;
    }

    public void setPasswordUser(String passwordUser) {
        this.passwordUser = passwordUser;
    }
}
