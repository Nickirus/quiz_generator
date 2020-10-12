import model.QuestionWithEmbodiments;
import model.Quiz;
import utils.io.reader.QuizDeserializer;
import utils.io.reader.ReaderFromCsv;
import utils.QuizCombinatorialUtil;
import utils.io.writer.QuizSerializer;
import utils.io.writer.QuizWriter;
import utils.io.writer.WriterToDoc;
import utils.io.writer.WriterToTxt;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//todo
// -предусмотреть случай, когда количество ответов в вопросах разное
// -при экспорте нужна возможность буквенной нумерации ответов.
//      Не забыть про то, что ответы тогда тоже должны быть в буквенном выражении
// -научиться шифровать сгенерированные тесты в сохраненном файлe
// -добавить qrCode в docx при экспорте
// -учитывать ограничение на количество вариантов
// -Добавить возможность генерировать больше вариантов, чем факториал количества вопросов
// -добавить автоматическое формирование имен экспортироемых файлов
// -графический интерфейс
// -предусмотреть возможность добавления другого типа вопроса в тест, например, с развернутым ответом
// -добавить отлов exception-ов
// -добавить логгирование

public class Main {

    public static void main(String[] args) {
        Quiz originalQuiz = getOriginalQuizFromResourceFile();
        List<Quiz> generatedQuizList = QuizCombinatorialUtil.generate(originalQuiz, 50, 5);

        QuizSerializer.serialize(generatedQuizList, "Quiz.quiz");
        List<Quiz> generatedQuizList2 = QuizDeserializer.deserialize("Quiz.quiz");

        writeQuizTextToFiles(generatedQuizList2);
    }

    private static void writeQuizTextToFiles(List<Quiz> generatedQuizList) {
        QuizWriter writer = new WriterToTxt();
        writer.writeQuizText(generatedQuizList, "1.txt");
        writer.writeQuizAnswers(generatedQuizList, "1_answers.txt");

        QuizWriter writerToDoc = new WriterToDoc();
        writerToDoc.writeQuizText(generatedQuizList, "quiz.docx");
        writerToDoc.writeQuizAnswers(generatedQuizList, "quiz_answers.docx");
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
