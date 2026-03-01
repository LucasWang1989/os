package nz.ac.sit.os.common.error.code;

import org.springframework.http.HttpStatus;

/**
 * @program: os
 * @description: common error codes
 * @author: Lucas Wang
 * @email: lucas.wang.1024@gmail.com
 * @date: 02/03/2026 01:16
 **/
public enum CommonErrorCodes implements ErrorCode{
    INTERNAL_SERVER_ERROR("COMMON-SYSTEM-001", HttpStatus.INTERNAL_SERVER_ERROR,
            "System is temporarily unavailable. Please try again later.", false),
    ;

    private final String code;
    private final HttpStatus httpStatus;
    private final String defaultMessage;
    private final boolean retryable;

    CommonErrorCodes(String code, HttpStatus httpStatus, String defaultMessage, boolean retryable) {
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