package engine.model.dto.response;

import java.util.List;
import java.util.Objects;

public class QuizDto {
    private int id;
    private String title;
    private String text;
    private List<String> options;

    public QuizDto() {
    }

    public QuizDto(int id, String title, String text, List<String> options) {
        this.id = id;
        this.title = title;
        this.text = text;
        this.options = options;
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

    @Override
    public boolean equals(Object o) {

        if (o == null || getClass() != o.getClass()) return false;
        QuizDto quizDto = (QuizDto) o;
        return id == quizDto.id && Objects.equals(title, quizDto.title) && Objects.equals(text, quizDto.text) && Objects.equals(options, quizDto.options);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, text, options);
    }

    @Override
    public String toString() {
        return "QuizDto{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", text='" + text + '\'' +
                ", options=" + options +
                '}';
    }
}
