import model.QuestionWithEmbodiments;
import model.Quiz;
import reader.ReaderFromCsv;
import utils.QuizCombinatorialUtil;
import writer.WriterToTxt;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//todo
// -предусмотреть случай, когда количество ответов в вопросах разное
// -увеличить количество вариантов, переставляя вопросы местами
//      например, в 2 вопросах, по 3 варианта ответа сейчас 6 сгенированных тестов, а должно быть 6^2=36
//      в 3 вопросах, по 4 варианта ответа сейчас 24 сгенированных тестов, а должно быть 24^3=13824
// -научиться записывать сгенерированные тесты в файл и читать из него
// -научиться шифровать этот файл
// -экспорт в .txt
// -экспорт в word https://www.baeldung.com/java-microsoft-word-with-apache-poi
// -графический интерфейс
// -предусмотреть возможность добавления другого типа вопроса в тест, например, с развернутым ответом
// -добавить отлов exception-ов
// -добавить логгирование

public class Main {

    public static void main(String[] args) {
        Quiz originalQuiz = getOriginalQuizFromResourceFile();
        List<Quiz> generatedQuizList = QuizCombinatorialUtil.generate(originalQuiz);
        writeToFile(generatedQuizList, "1.txt");
    }

    private static void writeToFile(List<Quiz> generatedQuizList, String filename) {
        WriterToTxt writer = new WriterToTxt(filename);
        writer.write(generatedQuizList);
    }

    private static Quiz getOriginalQuizFromResourceFile() {
        ReaderFromCsv reader = new ReaderFromCsv("ru_questions.csv");
        Quiz originalQuiz = new Quiz();
        for (String[] rawQuestion : reader.getLineList()) {
            String questionText = rawQuestion[0];
            Integer numberOfTrueAnswer = Integer.valueOf(rawQuestion[1]);

            List<String> answers = new ArrayList<>(Arrays.asList(rawQuestion).subList(2, rawQuestion.length));
            QuestionWithEmbodiments question = new QuestionWithEmbodiments(questionText, answers, numberOfTrueAnswer);
            originalQuiz.addNewQuestion(question);
        }
        return originalQuiz;
    }
}
