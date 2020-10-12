package model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Setter
@Getter
public class Quiz implements Serializable {
    private List<QuestionWithEmbodiments> questions = new ArrayList<>();
    private String uuid = UUID.randomUUID().toString();

    public void addNewQuestion(QuestionWithEmbodiments question) {
        this.questions.add(question);
    }
}
