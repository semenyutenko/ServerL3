package serverService;

import dbService.DBService;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import serverService.servlets.SignInServlet;
import serverService.servlets.SignUpServlet;

public class ServerService {

    private DBService dbService;
    private Server server;

    public ServerService(DBService dbService) throws ServerException {
        this.dbService = dbService;
        this.server = new Server(8080);
    }

    public void startServer() throws ServerException {
        SignUpServlet signUpServlet = new SignUpServlet(dbService);
        SignInServlet signInServlet = new SignInServlet(dbService);
        ServletContextHandler contextHandler = new ServletContextHandler(ServletContextHandler.SESSIONS);
        contextHandler.addServlet(new ServletHolder(signUpServlet), "/signup");
        contextHandler.addServlet(new ServletHolder(signInServlet), "/signin");
        server.setHandler(contextHandler);
        try {
            server.start();
            java.util.logging.Logger.getGlobal().info("Server started");
            server.join();
        } catch (Exception e) {
            throw new ServerException(e);
        }
    }
    public void stopServer(){
        try {
            server.stop();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
