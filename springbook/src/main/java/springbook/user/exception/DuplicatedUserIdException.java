package springbook.user.exception;

/**
 * Created by geekslife on 2017. 1. 22..
 */
public class DuplicatedUserIdException extends RuntimeException {
    public DuplicatedUserIdException(Throwable cause) {
        super(cause);
    }
}
