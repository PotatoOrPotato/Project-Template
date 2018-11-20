package unmanned.supermarket.pay.exception;

public class MyException extends Exception {

    private String message;

    public void MyException( String message){
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
