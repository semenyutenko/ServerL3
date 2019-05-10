package dbService;

import dbService.dao.UsersDAO;
import dbService.dataSets.UserDataSet;
import org.h2.jdbcx.JdbcDataSource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBService {
    private final Connection connection;

    public DBService(){
        this.connection = getH2Connection();
    }
    
    public void addUser(String login, String password) throws DBException{
        try{
            connection.setAutoCommit(false);
            UsersDAO dao = new UsersDAO(connection);
            dao.createTable();
            int addedUsers = dao.insertUser(login, password);
            connection.commit();
            System.out.println("Added " + addedUsers + " users.");            
        }catch (SQLException e){
            try {
                connection.rollback();
            }catch (SQLException ignore){}
            throw new DBException(e);            
        }finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ignore) {}
        }
    }
    
    public boolean isAuthorized(String login, String password) throws DBException{
        try {
            UsersDAO dao = new UsersDAO(connection);
            for(UserDataSet user: dao.getUser(login)){
                if(password.endsWith(user.getPassword())) return true;
            }
            return false;
        }catch (SQLException e){
            throw new DBException(e);
        }
    }

    private Connection getH2Connection() {     
        try
            {String url = "jdbc:h2:./h2db";
            String name = "test";
            String pass = "test";

            JdbcDataSource ds = new JdbcDataSource();
            ds.setURL(url);
            ds.setUser(name);
            ds.setPassword(pass);
            Connection connection = DriverManager.getConnection(url, name, pass);
            return connection;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
