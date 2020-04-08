package model.exception;


/**Exception for getting information about airline*/
public class AirlineException extends Exception {

	/**Default serial version uid*/
	private static final long serialVersionUID = 1L;
	
	/**
     * Constructor with specified exception message
     * @param message - exception message
     */
    public AirlineException(String message) {
        super(message);
    }

    /**
     * Constructor with specified exception message and exception
     * @param message - exception message
     * @param e - appeared error
     */
    public AirlineException(String message, Throwable e) {
        super(message, e);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }

    @Override
    public void printStackTrace() {
        super.printStackTrace();
    }

}
