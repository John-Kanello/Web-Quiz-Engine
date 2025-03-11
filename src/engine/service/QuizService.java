package engine.service;

import engine.model.quiz.Quiz;
import engine.model.quiz.QuizCreationDto;
import engine.repository.QuizRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {
    private final QuizRepository quizRepository;

    public QuizService(QuizRepository repository) {
        this.quizRepository = repository;
    }

    public Optional<Quiz> findById(int id) {
        return quizRepository.findById(id);
    }

    public Page<Quiz> findAll(Pageable pageable) {
        return quizRepository.findAll(pageable);
    }

    public Quiz uploadQuiz(QuizCreationDto quizCreationDto) {
        Quiz quiz = new Quiz(
                quizCreationDto.getTitle(),
                quizCreationDto.getText(),
                quizCreationDto.getOptions(),
                quizCreationDto.getAnswer() == null ? Collections.emptyList() : quizCreationDto.getAnswer(),
                quizCreationDto.getUser());
        quizRepository.save(quiz);
        return quiz;
    }

    public void deleteById(int id) {
        quizRepository.deleteById(id);
    }
}
