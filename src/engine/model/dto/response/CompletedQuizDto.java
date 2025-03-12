package engine.model.dto.response;

import java.time.LocalDateTime;

public record CompletedQuizDto(
        int id,
        LocalDateTime completedAt
) {}

