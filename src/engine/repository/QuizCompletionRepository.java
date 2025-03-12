package engine.repository;

import engine.model.entity.CompletedQuiz;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuizCompletionRepository extends JpaRepository<CompletedQuiz, Integer> {
    Page<CompletedQuiz> findByUserId(int id, Pageable pageable);
}
