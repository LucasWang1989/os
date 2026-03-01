package nz.ac.sit.os.common.error.code;

import org.springframework.http.HttpStatus;

public interface ErrorCode {
    String code();
    HttpStatus httpStatus();
    String defaultMessage();
    boolean retryable();
}
