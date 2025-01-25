package graber.thomas.feastverse.exception;

public class InvalidNullProperty extends RuntimeException {
    public InvalidNullProperty(String propertiesName) {
        super(propertiesName + " cannot be null");
    }
}
