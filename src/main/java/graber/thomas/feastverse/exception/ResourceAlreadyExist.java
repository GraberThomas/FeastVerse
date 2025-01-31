package graber.thomas.feastverse.exception;

public class ResourceAlreadyExist extends RuntimeException {
    public ResourceAlreadyExist(String nameofResource) {
        super(nameofResource);
    }
}
