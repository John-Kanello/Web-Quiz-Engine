package engine.model.quiz;

import engine.model.user.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;
import java.util.Objects;

public class QuizCreationDto {
    @NotBlank(message = "title is required")
    private String title;
    @NotBlank(message = "text is required")
    private String text;
    @Size(min = 2)
    @NotNull
    private List<String> options;
    private List<Integer> answer;
    private User user;

    public QuizCreationDto() {
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

    @Override
    public boolean equals(Object o) {

        if (o == null || getClass() != o.getClass()) return false;
        QuizCreationDto that = (QuizCreationDto) o;
        return Objects.equals(title, that.title) && Objects.equals(text, that.text) && Objects.equals(options, that.options) && Objects.equals(answer, that.answer) && Objects.equals(user, that.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, text, options, answer, user);
    }

    @Override
    public String toString() {
        return "QuizCreationDto{" +
                "title='" + title + '\'' +
                ", text='" + text + '\'' +
                ", options=" + options +
                ", answer=" + answer +
                ", user=" + user +
                '}';
    }
}
