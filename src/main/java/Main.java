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
// -при экспорте нужна возможность буквенной нумерации ответов.
//      Не забыть про то, что ответы тогда тоже должны быть в буквенном выражении
// -добавить ограничение на количество вопросов в тесте
// -научиться записывать сгенерированные тесты в файл и читать из него
// -научиться шифровать этот файл
// -экспорт в .txt
// -добавить автоматическое формирование имен экспортироемых файлов
// -экспорт в word https://www.baeldung.com/java-microsoft-word-with-apache-poi
// -графический интерфейс
// -предусмотреть возможность добавления другого типа вопроса в тест, например, с развернутым ответом
// -добавить отлов exception-ов
// -добавить логгирование

public class Main {

    public static void main(String[] args) {
        Quiz originalQuiz = getOriginalQuizFromResourceFile();
        List<Quiz> generatedQuizList = QuizCombinatorialUtil.generate(originalQuiz, 50);
        writeQuizTextToFiles(generatedQuizList);
    }

    private static void writeQuizTextToFiles(List<Quiz> generatedQuizList) {
        WriterToTxt writer = new WriterToTxt();
        writer.writeQuizText(generatedQuizList, "1.txt");
        writer.writeQuizAnswersToText(generatedQuizList, "1_answers.txt");
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
