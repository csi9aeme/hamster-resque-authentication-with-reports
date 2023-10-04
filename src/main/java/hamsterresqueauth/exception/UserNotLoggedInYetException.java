package hamsterresqueauth.exception;

public class UserNotLoggedInYetException extends RuntimeException{
    public UserNotLoggedInYetException() {
        super("User not logged in yet!");
    }

}
