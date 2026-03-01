package nz.ac.sit.os.common.error.code;

import org.springframework.http.HttpStatus;

/**
 * @program: os
 * @description: Error code definition
 * @author: Lucas Wang
 * @email: lucas.wang.1024@gmail.com
 * @date: 01/03/2026 21:55
 **/
public enum OrderErrorCodes implements ErrorCode {
    NOT_FOUND("ORDER-NOT_FOUND-001", HttpStatus.NOT_FOUND, "Order not found.", false),
    STATE_CONFLICT("ORDER-CONFLICT-001", HttpStatus.CONFLICT, "Order status conflict.", false);

    private final String code;
    private final HttpStatus httpStatus;
    private final String defaultMessage;
    private final boolean retryable;

    OrderErrorCodes(String code, HttpStatus httpStatus, String defaultMessage, boolean retryable) {
        this.code = code;
        this.httpStatus = httpStatus;
        this.defaultMessage = defaultMessage;
        this.retryable = retryable;
    }

    public String code() { return code; }
    public HttpStatus httpStatus() { return httpStatus; }
    public String defaultMessage() { return defaultMessage; }
    public boolean retryable() { return retryable; }
}