package mongodb.exception;

public class BookNotFoundException extends RepositoryException {

    public BookNotFoundException(String message) {
        super(message);
    }


}
