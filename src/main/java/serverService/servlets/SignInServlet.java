package serverService.servlets;

import dbService.DBException;
import dbService.DBService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SignInServlet extends HttpServlet {
    private DBService dbService;

    public SignInServlet(DBService dbService){
        this.dbService = dbService;
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try{
            if(dbService.isAuthorized(request.getParameter("login"), request.getParameter("password"))){
                response.setStatus(HttpServletResponse.SC_OK);
                response.setContentType("text/html;charset=utf-8");
                response.getWriter().println("Authorized: " + request.getParameter("login"));
                System.out.println("User '" + request.getParameter("login") + " # " +
                        request.getParameter("password") + "' is authorized.");
            }else{
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.setContentType("text/html;charset=utf-8");
                response.getWriter().println("Unauthorized");
                System.out.println("User '" + request.getParameter("login") + " # " +
                        request.getParameter("password") + "' is unauthorized.");
            }
        }catch (DBException e){}
    }
}
