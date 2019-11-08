import java.util.List;

public class AccountManager {

    private static AccountManager single_instance = null;

    public static AccountManager getInstance(){
        if (single_instance == null)
            single_instance = new AccountManager();
        return single_instance;
    }
    public void createAccount(){
        //loadAccount
        //write into CSV
    }
    public loadAccount(){
        //read in CSV
    }
    public updateAccount(){
        //loadAccount
        //search row in CSV, write in CSV
    }
    public deleteAccount(){
        //loadAccount
        //
    }

}
