package be.vdab.welkom.exeptions;

public class RepositoryException extends RuntimeException{
    public RepositoryException(Exception cause) {
        super(cause);
    }
}
