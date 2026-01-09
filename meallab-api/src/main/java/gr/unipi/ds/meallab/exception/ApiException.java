package gr.unipi.ds.meallab.exception;

public class ApiException extends Exception{

	private final ErrorType errorType;
    
    public enum ErrorType {
        NETWORK_ERROR,      // Connection issues
        INVALID_RESPONSE,   // Bad JSON or unexpected format
        NOT_FOUND,          // Meal not found
        INVALID_INPUT       // Empty or null parameters
    }
    
    public ApiException(String message, ErrorType errorType) {
        super(message);
        this.errorType = errorType;
    }
    
    public ApiException(String message, ErrorType errorType, Throwable cause) {
        super(message, cause);
        this.errorType = errorType;
    }
    
    public ErrorType getErrorType() {
        return errorType;
    }
    
    @Override
    public String toString() {
        return "MealApiException{" +
                "errorType=" + errorType +
                ", message='" + getMessage() + '\'' +
                '}';
    }
}
