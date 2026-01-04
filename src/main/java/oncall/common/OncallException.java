package oncall.common;

public class OncallException extends IllegalArgumentException {
    public OncallException(String message) {
        super("[ERROR] " + message);
    }
}
