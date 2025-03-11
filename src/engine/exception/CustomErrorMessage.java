package engine.exception;

import java.time.LocalDateTime;

public record CustomErrorMessage(
        int statusCode,
        LocalDateTime timestamp,
        String message,
        String description
) {}
