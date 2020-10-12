package utils.io.reader;

import lombok.experimental.UtilityClass;
import model.Quiz;

import java.io.*;
import java.util.List;

@UtilityClass
public class QuizDeserializer {
    public List<Quiz> deserialize(String fileName) {
        List<Quiz> quizList = null;
        try {
            FileInputStream inputStream = new FileInputStream(fileName);
            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
            quizList = (List<Quiz>) objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return quizList;
    }
}
