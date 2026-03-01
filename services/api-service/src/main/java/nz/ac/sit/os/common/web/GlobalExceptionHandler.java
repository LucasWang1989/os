package nz.ac.sit.os.common.web;

import lombok.extern.slf4j.Slf4j;
import nz.ac.sit.os.common.error.ApiError;
import nz.ac.sit.os.common.error.BizException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BizException.class)
    public ResponseEntity<ApiError> handleBiz(BizException ex, HttpServletRequest req) {
        String traceId = traceId();

        // Business exception: warn（unless very important）
        log.warn("traceId={} code={} status={} path={} internal={}",
                traceId, ex.getErrorCode().code(), ex.getErrorCode().httpStatus(), req.getRequestURI(), ex.getInternalMessage(), ex);

        Optional<String> safeMessage = Optional.ofNullable(ex.getSafeMessage());
        ApiError body = new ApiError(
                traceId,
                ex.getErrorCode().code(),
                safeMessage.orElse(ex.getErrorCode().defaultMessage()),
                null, // Suggest null in production env；
                Instant.now()
        );
        return ResponseEntity.status(ex.getErrorCode().httpStatus()).body(body);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> handleValidation(MethodArgumentNotValidException ex, HttpServletRequest req) {
        String traceId = traceId();
        String msg = ex.getBindingResult().getFieldErrors().stream()
                .findFirst()
                .map(fe -> fe.getField() + " " + fe.getDefaultMessage())
                .orElse("Invalid request");

        log.info("traceId={} validationFail path={} msg={}", traceId, req.getRequestURI(), msg);

        ApiError body = new ApiError(traceId, "COMMON-VALIDATION-001", msg, null, Instant.now());
        return ResponseEntity.badRequest().body(body);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> handleUnknown(Exception ex, HttpServletRequest req) {
        String traceId = traceId();

        // System exception：error
        log.error("traceId={} unexpected path={}", traceId, req.getRequestURI(), ex);

        ApiError body = new ApiError(
                traceId,
                "COMMON-SYSTEM-001",
                "System is temporarily unavailable. Please try again later.",
                null,
                Instant.now()
        );
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(body);
    }

    private String traceId() {
        return Optional.ofNullable(org.slf4j.MDC.get("traceId"))
                .orElse(UUID.randomUUID().toString());
    }
}