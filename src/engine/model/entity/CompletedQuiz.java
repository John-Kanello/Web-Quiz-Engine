package engine.model.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table
public class CompletedQuiz {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int userId;
    private LocalDateTime completedAt;
    @ManyToOne
    @JoinColumn(name = "quiz_id", nullable = false)
    private Quiz quiz;

    public CompletedQuiz() {}

    public CompletedQuiz(int userId, LocalDateTime completedAt, Quiz quiz) {
        this.userId = userId;
        this.completedAt = completedAt;
        this.quiz = quiz;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public LocalDateTime getCompletedAt() {
        return completedAt;
    }

    public void setCompletedAt(LocalDateTime completedAt) {
        this.completedAt = completedAt;
    }

    public Quiz getQuiz() {
        return quiz;
    }

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        CompletedQuiz that = (CompletedQuiz) o;
        return id == that.id && userId == that.userId && Objects.equals(completedAt, that.completedAt) && Objects.equals(quiz, that.quiz);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, completedAt, quiz);
    }

    @Override
    public String toString() {
        return "CompletedQuiz{" +
                "id=" + id +
                ", userId=" + userId +
                ", completedAt=" + completedAt +
                ", quiz=" + quiz +
                '}';
    }
}
