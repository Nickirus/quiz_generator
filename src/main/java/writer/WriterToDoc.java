package writer;

import model.QuestionWithEmbodiments;
import model.Quiz;
import org.apache.poi.xwpf.usermodel.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class WriterToDoc {

    public void writeQuizText(List<Quiz> quizList, String fileName) {
        XWPFDocument document = new XWPFDocument();
        int variant = 1;
        for (Quiz quiz : quizList) {
            variant = fillVariantHeader(document, variant, quiz.getUuid());

            XWPFParagraph paragraph = document.createParagraph();
            XWPFRun textRun = paragraph.createRun();
            textRun.setFontSize(12);
            textRun.setFontFamily("Times New Roman");
            textRun.addBreak();

            List<QuestionWithEmbodiments> questions = quiz.getQuestions();
            int questionNum = 1;
            for (QuestionWithEmbodiments question : questions) {
                textRun.setText("Вопрос №" + questionNum++);
                textRun.addBreak();
                String questionText = question.getQuestionText();
                textRun.setText(questionText);
                textRun.addBreak();
                List<String> answers = question.getAnswers();
                int answerNum = 1;
                for (String answer : answers) {
                    textRun.setText(answerNum++ + ". " + answer);
                    textRun.addBreak();
                }
                textRun.addBreak();
            }
            textRun.addBreak(BreakType.PAGE);
        }
        write(fileName, document);
    }

    public void writeQuizAnswersToText(List<Quiz> quizList, String fileName) {
        XWPFDocument document = new XWPFDocument();
        int variant = 1;
        for (Quiz quiz : quizList) {
            variant = fillVariantHeader(document, variant, quiz.getUuid());

            XWPFParagraph paragraph = document.createParagraph();
            XWPFRun textRun = paragraph.createRun();
            textRun.setFontSize(12);
            textRun.setFontFamily("Times New Roman");
            textRun.addBreak();

            List<QuestionWithEmbodiments> questions = quiz.getQuestions();
            int questionNum = 1;
            for (QuestionWithEmbodiments question : questions) {
                textRun.setText("Вопрос №" + questionNum++ +" - " + question.getNumberOfTrueAnswer());
                textRun.addBreak();
                textRun.addBreak();
            }
            textRun.addBreak(BreakType.PAGE);
        }
        write(fileName, document);
    }

    private int fillVariantHeader(XWPFDocument document, int variant, String uuid) {
        XWPFParagraph paragraph = document.createParagraph();
        paragraph.setAlignment(ParagraphAlignment.CENTER);
        XWPFRun headerRun = paragraph.createRun();

        headerRun.setFontSize(14);
        headerRun.setBold(true);
        headerRun.setFontFamily("Times New Roman");

        headerRun.setText("Вариант " + variant++);

        XWPFParagraph uuidParagraph = document.createParagraph();
        uuidParagraph.setAlignment(ParagraphAlignment.CENTER);
        XWPFRun uuidRun = uuidParagraph.createRun();
        uuidRun.setFontSize(7);
        uuidRun.setFontFamily("Courier New");
        uuidRun.setText(uuid);
        uuidRun.addBreak();
        return variant;
    }

    private void write(String fileName, XWPFDocument document) {
        try {
            document.write(new FileOutputStream(new File(fileName)));
            System.out.println("Successfully wrote to the " + fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
