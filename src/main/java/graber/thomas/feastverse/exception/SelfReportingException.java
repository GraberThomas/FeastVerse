package graber.thomas.feastverse.exception;

public class SelfReportingException extends RuntimeException {
    public SelfReportingException(String message) {
        super(message);
    }
}
