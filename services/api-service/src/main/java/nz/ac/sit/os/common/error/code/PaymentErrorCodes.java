package nz.ac.sit.os.common.error.code;

import org.springframework.http.HttpStatus;

public enum PaymentErrorCodes implements ErrorCode {
    IDEMPOTENCY_CONFLICT("PAY-CONFLICT-001", HttpStatus.CONFLICT, "Duplicate request.", false),
    PAYMENT_PROVIDER_UNAVAILABLE("PAY-UPSTREAM-001", HttpStatus.BAD_GATEWAY,
            "Payment provider is unavailable. Please try again later.", true),
    PAYLOAD_INVALID("PAY-VALIDATION-001", HttpStatus.BAD_REQUEST,
            "Payload from payment provider is invalid.", true);

    private final String code;
    private final HttpStatus httpStatus;
    private final String defaultMessage;
    private final boolean retryable;

    PaymentErrorCodes(String code, HttpStatus httpStatus, String defaultMessage, boolean retryable) {
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
