package engine.service;

import engine.model.entity.CompletedQuiz;
import engine.repository.QuizCompletionRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class QuizCompletionService {
    private final QuizCompletionRepository quizCompletionRepository;

    public QuizCompletionService(QuizCompletionRepository completedQuizRepository) {
        this.quizCompletionRepository = completedQuizRepository;
    }

    public Page<CompletedQuiz> findByUserId(int userId, Pageable pageable) {
        return quizCompletionRepository.findByUserId(userId, pageable);
    }

    public void save(CompletedQuiz completedQuiz) {
        quizCompletionRepository.save(completedQuiz);
    }
}
