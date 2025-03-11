package engine.model.quiz;

import engine.model.user.User;
import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

@Entity
@Table
public class Quiz {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private String title;
    @Column
    private String text;
    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> options;
    @ElementCollection(fetch = FetchType.EAGER)
    private List<Integer> answer;
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    @OneToMany(mappedBy = "quiz", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CompletedQuiz> completedQuizzes;

    public Quiz() {}

    public Quiz(String title, String text, List<String> options, List<Integer> answer, User user) {
        this.title = title;
        this.text = text;
        this.options = options;
        this.answer = answer;
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<String> getOptions() {
        return options;
    }

    public void setOptions(List<String> options) {
        this.options = options;
    }

    public List<Integer> getAnswer() {
        return answer;
    }

    public void setAnswer(List<Integer> answer) {
        this.answer = answer;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<CompletedQuiz> getCompletedQuizzes() {
        return completedQuizzes;
    }

    public void setCompletedQuizzes(List<CompletedQuiz> completedQuizzes) {
        this.completedQuizzes = completedQuizzes;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Quiz quiz = (Quiz) o;
        return id == quiz.id && Objects.equals(title, quiz.title) && Objects.equals(text, quiz.text) && Objects.equals(options, quiz.options) && Objects.equals(answer, quiz.answer) && Objects.equals(user, quiz.user) && Objects.equals(completedQuizzes, quiz.completedQuizzes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, text, options, answer, user, completedQuizzes);
    }

    @Override
    public String toString() {
        return "Quiz{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", text='" + text + '\'' +
                ", options=" + options +
                ", answer=" + answer +
                ", user=" + user +
                ", completedQuizzes=" + completedQuizzes +
                '}';
    }
}
