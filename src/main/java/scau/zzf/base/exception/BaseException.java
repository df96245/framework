package scau.zzf.base.exception;

/**
 */
public class BaseException  extends Exception {

    public BaseException(ERROR message) {
        super(message.toString());
    }

    public BaseException(Throwable e, ERROR message) {
        super(message.toString(), e);
    }

}
