package engine.model.quiz;

import java.time.LocalDateTime;

public record CompletedQuizDto(
        int id,
        LocalDateTime completedAt
) {}

