package nz.ac.sit.os.common.error;

import lombok.Getter;
import nz.ac.sit.os.common.error.code.ErrorCode;
import org.springframework.http.HttpStatus;

@Getter
public class BizException extends RuntimeException {
    private final ErrorCode errorCode;
    private final String safeMessage;      // support recovering message
    private final String internalMessage;  // for logs

    public BizException(ErrorCode errorCode, String safeMessage, String internalMessage, Throwable cause) {
        super(internalMessage, cause);
        this.errorCode = errorCode;
        this.safeMessage = safeMessage;
        this.internalMessage = internalMessage;
    }

    public BizException(ErrorCode errorCode, String internalMessage, Throwable cause) {
        this(errorCode, errorCode.defaultMessage(), internalMessage, cause);
    }

    public BizException(ErrorCode errorCode, String internalMessage) {
        this(errorCode, errorCode.defaultMessage(), internalMessage, null);
    }

    public String code() { return errorCode.code(); }
    public HttpStatus status() { return errorCode.httpStatus(); }
    public boolean retryable() { return errorCode.retryable(); }
    public String safeMessage() { return safeMessage; }
    public String internalMessage() { return internalMessage; }
}
