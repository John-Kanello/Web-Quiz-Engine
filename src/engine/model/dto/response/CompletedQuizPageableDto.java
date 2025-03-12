package engine.model.dto.response;

import java.util.List;

public record CompletedQuizPageableDto(
        int totalPages,
        long totalElements,
        boolean last,
        boolean first,
        boolean empty,
        List<CompletedQuizDto> content
) {}
