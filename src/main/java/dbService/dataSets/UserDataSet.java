package dbService.dataSets;

import java.io.Serializable;

public class UserDataSet implements Serializable {

    private String login;
    private String password;

    public UserDataSet(String login, String password){
        this.setLogin(login);
        this.setPassword(password);
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password){
        this.password = password;
    }


    public String getLogin() {
        return login;
    }

    public String getPassword(){
        return password;
    }
}
