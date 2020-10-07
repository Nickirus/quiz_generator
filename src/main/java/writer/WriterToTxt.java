package writer;

import model.QuestionWithEmbodiments;
import model.Quiz;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class WriterToTxt {

    public void writeQuizText(List<Quiz> quizList, String fileName) {
        try {
            FileWriter myWriter = new FileWriter(fileName);
            int variant = 1;
            for (Quiz quiz : quizList) {
                myWriter.write("----------------------------------------------------" + "\n");
                myWriter.write("Вариант " + variant++ + "\n");
                String uuid = quiz.getUuid();
                List<QuestionWithEmbodiments> questions = quiz.getQuestions();
                myWriter.write(uuid + "\n");
                myWriter.write("\n");
                int questionNum = 1;
                for (QuestionWithEmbodiments question : questions) {
                    myWriter.write("Вопрос №" + questionNum++ + "\n");
                    String questionText = question.getQuestionText();
                    myWriter.write(questionText + "\n");
                    List<String> answers = question.getAnswers();
                    int answerNum = 1;
                    for (String answer : answers) {
                        myWriter.write(answerNum++ + ". " + answer + "\n");
                    }
                    myWriter.write("\n");
                }
                myWriter.write("\n");
            }
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public void writeQuizAnswersToText(List<Quiz> quizList, String fileName) {
        try {
            FileWriter myWriter = new FileWriter(fileName);
            int variant = 1;
            for (Quiz quiz : quizList) {
                myWriter.write("----------------------------------------------------" + "\n");
                myWriter.write("Вариант " + variant++ + "\n");
                String uuid = quiz.getUuid();
                List<QuestionWithEmbodiments> questions = quiz.getQuestions();
                myWriter.write(uuid + "\n");
                myWriter.write("\n");
                int questionNum = 1;
                for (QuestionWithEmbodiments question : questions) {
                    myWriter.write("Вопрос №" + questionNum++ + " - " + question.getNumberOfTrueAnswer());
                    myWriter.write("\n");
                }
                myWriter.write("\n");
            }
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}