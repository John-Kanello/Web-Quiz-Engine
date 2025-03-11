package engine.util;

import engine.model.quiz.Quiz;
import engine.model.quiz.QuizDto;
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
