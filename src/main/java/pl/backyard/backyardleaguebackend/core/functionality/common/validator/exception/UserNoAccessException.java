package pl.backyard.backyardleaguebackend.core.functionality.common.validator.exception;

public class UserNoAccessException extends RuntimeException {
    public UserNoAccessException(String message) {
        super(message);
    }
}
