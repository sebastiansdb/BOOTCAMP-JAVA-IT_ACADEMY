package S05T01N03.exceptions;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String resourceName, String fieldName, Object fieldValue) {
        super (String.format("%s was not found with: %s = %s", resourceName, fieldName, fieldValue));
    }
}
