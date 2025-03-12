package engine.util;

import engine.model.entity.Quiz;
import engine.model.dto.response.QuizDto;
import org.springframework.stereotype.Component;

@Component
public class QuizMapper implements Mapper<Quiz, QuizDto> {

    public QuizDto toDto(Quiz quiz) {
        return new QuizDto(
                quiz.getId(),
                quiz.getTitle(),
                quiz.getText(),
                quiz.getOptions()
        );
    }
}
