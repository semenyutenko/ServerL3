package dbService.dao;

import dbService.dataSets.UserDataSet;
import dbService.executor.Executor;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsersDAO {
    private Executor executor;

    public UsersDAO(Connection connection){
        this.executor = new Executor(connection);
    }

    public void createTable()throws SQLException {
        executor.execUpdate("create table if not exists users (id bigint auto_increment, " +
                "login varchar(256), password varchar(256), primary key (id))");
    }

    public int insertUser(String login, String password) throws SQLException{
        int inserted = executor.execUpdate("insert into users (login, password) values" +
                " ('" + login + "', '" + password + "')");
        return inserted;
    }

    public List<UserDataSet> getUser(String login) throws SQLException{
        List<UserDataSet> userList = executor.execQuery("select * from users where login='" + login + "'",
                result ->{List<UserDataSet> users = new ArrayList<UserDataSet>();
                while (result.next()){
                    users.add(new UserDataSet(result.getString("login"),
                            result.getString("password")));
                }
                return users;
                });
        return userList;
    }
}
