package serverService;

public class ServerException extends Exception {
    public ServerException (Throwable throwable){
        super(throwable);
        throwable.printStackTrace();
    }
}
