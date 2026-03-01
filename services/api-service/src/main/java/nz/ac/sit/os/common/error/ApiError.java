package nz.ac.sit.os.common.error;

import java.time.Instant;

public record ApiError(
        String traceId,     // Unique request identifier for cross-service tracing
        String code,        // Application-specific error code (contract-level stable value)
        String message,     // Client-facing message (safe for exposure)
        String details,     // Internal diagnostic details (dev/staging only)
        Instant timestamp   // Error occurrence time (UTC recommended)
) {}
