package model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
public class QuestionWithEmbodiments implements Serializable {
    private final String questionText;
    private final List<String> answers;
    private final Integer numberOfTrueAnswer;

    public int getIndexOfTrueAnswer() {
        return this.numberOfTrueAnswer - 1;
    }
}
