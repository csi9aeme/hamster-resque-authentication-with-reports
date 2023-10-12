package hamsterresqueauth.exception;

public class UsernameAlreadyExistException extends RuntimeException{
    public UsernameAlreadyExistException() {
        super("This email is already in database.");
    }

}
