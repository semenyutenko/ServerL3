package serverService.servlets;

import dbService.DBException;
import dbService.DBService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SignUpServlet extends HttpServlet {
    private DBService dbService;

    public SignUpServlet(DBService dbService){
        this.dbService = dbService;
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response){
        try {
            dbService.addUser(request.getParameter("login"), request.getParameter("password"));
            System.out.println("User '" + request.getParameter("login") + "' is added. Password is '" +
                    request.getParameter("password") + "'.");
        } catch (DBException e) {
            e.printStackTrace();
        }
    }

}
