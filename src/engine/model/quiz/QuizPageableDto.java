package engine.model.quiz;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

public record QuizPageableDto (
        int totalPages,
        long totalElements,
        boolean last,
        boolean first,
        Sort sort,
        int number,
        int numberOfElements,
        int size,
        boolean empty,
        Pageable pageable,
        List<QuizDto> content
) {}
