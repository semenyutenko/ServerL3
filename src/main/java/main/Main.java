package main;

import dbService.DBService;
import serverService.ServerException;
import serverService.ServerService;

public class Main {
    public static void main(String[] args) throws ServerException {
        DBService dbService = new DBService();

        ServerService serverService = new ServerService(dbService);
        serverService.startServer();
    }
}
