package accounts;

public class StaffAccount {
    //Attributes
    private String accountID;
    private String username;
    private String password;

    public StaffAccount(String username, String password) {
        this.username = username;
        this.password = password;
    }


    //Methods
    //Getters
    public String getAccountID() {
        return accountID;
    }
    public String getUsername() {
        return username;
    }
    public String getPassword() {
        return password;
    }

    //Setters
    public void setAccountID(String accountID) {
        this.accountID = accountID;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public void setPassword(String password) {
        this.password = password;
    }
}
