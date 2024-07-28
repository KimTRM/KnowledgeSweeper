package GameEngine.Util.Game;

import GameEngine.Graphics.AssetManager;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.List;

public class QuizManager {
    AssetManager assetManager;

    // ----- QUESTION RANDOMIZER -----
    public String Question;
    public String A, B, C, D;
    public String correctAnswer;
    public String category;
    public boolean Science, History, Math;

    File file[] = new File[5];
    List<Question> questionList = new ArrayList<>();
    public int x;

    // ----- ANSWER CHECKER -----
    public boolean AnswerCorrect;
    public boolean AnswerWrong;

    // ----- CLICK HANDLER -----
    public boolean ansA, ansB, ansC, ansD, Confirm;

    public QuizManager(AssetManager assetManager) {
        this.assetManager = assetManager;
    }

    public void ChangeSubject(String Subject) {
        switch (Subject) {
            case "Science":
                if (Science == true && History == false && Math == false) {
                    file[0] = new File("GameEngine/Graphics/res/files/FILEQUESTIONSCIENCE.txt");
                    getQuestion(0);
                }
                break;
            case "History":
                if (History == true && Science == false && Math == false) {
                    file[1] = new File("GameEngine/Graphics/res/files/FILEQUESTIONHISTORY.txt");
                    getQuestion(1);
                }
                break;
            case "Math":
                if (Math == true && Science == false && History == false) {
                    file[2] = new File("GameEngine/Graphics/res/files/FILEQUESTIONSMATH.txt");
                    getQuestion(2);
                }
                break;
        }
    }

    public void getQuestion(int x) {
        Scanner inputFile;

        try {
            inputFile = new Scanner(file[x]);

            StringBuilder fileContent = new StringBuilder();

            while (inputFile.hasNext()) {
                String s = inputFile.nextLine();
                fileContent.append(s).append("\n");
            }

            String[] perQuestion = fileContent.toString().split("///");

            // Populate questionList with Question objects
            for (String questionString : perQuestion) {
                // Skip empty lines
                if (questionString.trim().isEmpty()) {
                    continue;
                }
                String[] token = questionString.split("\n");
                if (token.length >= 6) {
                    Question question = new Question();
                    question.question = token[1];
                    question.first = token[2];
                    question.second = token[3];
                    question.third = token[4];
                    question.fourth = token[5];
                    question.correctAnswer = token[6];

                    questionList.add(question);
                } else {
                    System.out.println("Incomplete question format: " + questionString);
                }
            }

            random();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void clear() {
        questionList.clear();
    }

    public void random() {
        Collections.shuffle(questionList);
        // Ask questions and get user's answers
        for (Question question : questionList) {
            Question = question.question;
            A = question.first;
            B = question.second;
            C = question.third;
            D = question.fourth;
            correctAnswer = question.correctAnswer;
        }
    }

    public String getAnswer() {

        if (ansA == true) {
            return "A";
        } else if (ansB == true) {
            return "B";
        } else if (ansC == true) {
            return "C";
        } else if (ansD == true) {
            return "D";
        }

        return null;
    }

    public void checkAnswer() {
        String userAnswer = getAnswer();
        if (userAnswer != null) {
            if (userAnswer.equals(correctAnswer)) {
                AnswerCorrect = true;
                AnswerWrong = false;
                assetManager.playSE(2);

            } else {
                AnswerCorrect = false;
                AnswerWrong = true;
                assetManager.playSE(3);
            }
        }
    }

    public boolean QuizReady() {
        if (Confirm) {
            return false;
        }


        return true;
    }

    public void update() {
        inQA = assetManager.TextBoxCollision(TextBoxAX, TextBoxAY, 150, 160, 300, 250, false, "A");
        inQB = assetManager.TextBoxCollision(TextBoxBX, TextBoxBY, 220, 160, 300, 250, false, "B");
        inQC = assetManager.TextBoxCollision(TextBoxCX, TextBoxCY, 280, 160, 300, 250, false, "C");
        inQD = assetManager.TextBoxCollision(TextBoxDX, TextBoxDY, 350, 160, 300, 250, false, "D");
    }

    public void input() {
        if (inQA == true) {
            ansA = true;
            ansB = false;
            ansC = false;
            ansD = false;
            Confirm = true;
        }

        if (inQB == true) {
            ansA = false;
            ansB = true;
            ansC = false;
            ansD = false;
            Confirm = true;
        }

        if (inQC == true) {
            ansA = false;
            ansB = false;
            ansC = true;
            ansD = false;
            Confirm = true;
        }

        if (inQD == true) {
            ansA = false;
            ansB = false;
            ansC = false;
            ansD = true;
            Confirm = true;
        }

        if (Confirm == true) {
            checkAnswer();
            random();

        }
    }

    public void render(Graphics2D g) {
        g.drawImage(AssetManager.Shade, 0, 0, assetManager.getScreenWidth(), assetManager.getScreenHeight(), null);

        assetManager.TextBox(250, 10, 800, 400, g);

        assetManager.TextBox(TextBoxAX, TextBoxAY, 250, 150, g);
        assetManager.TextBox(TextBoxBX, TextBoxBY, 250, 150, g);
        assetManager.TextBox(TextBoxCX, TextBoxCY, 250, 150, g);
        assetManager.TextBox(TextBoxDX, TextBoxDY, 250, 150, g);

        g.setColor(Color.BLACK);
        int Qy = 180;
        int Qx = 320;
        assetManager.PrintTexts(Question, Qx, Qy, 48, 55, false, g);

        assetManager.PrintText(A, TextBoxAX + 25, TextBoxAY + 80, 48, 25, false, g);
        assetManager.PrintText(B, TextBoxBX + 25, TextBoxBY + 80, 48, 25, false, g);
        assetManager.PrintText(C, TextBoxCX + 25, TextBoxCY + 80, 48, 25, false, g);
        assetManager.PrintText(D, TextBoxDX + 25, TextBoxDY + 80, 48, 25, false, g);

        // -- HOVER (A,B,C,D) --
        if (inQA == true) {
            g.drawImage(assetManager.Select1, TextBoxAX, TextBoxAY, 250, 150, null);
        }
        if (inQB == true) {
            g.drawImage(assetManager.Select1, TextBoxBX, TextBoxBY, 250, 150, null);
        }
        if (inQC == true) {
            g.drawImage(assetManager.Select1, TextBoxCX, TextBoxDY, 250, 150, null);
        }
        if (inQD == true) {
            g.drawImage(assetManager.Select1, TextBoxDX, TextBoxDY, 250, 150, null);
        }

    }

    boolean inQA, inQB, inQC, inQD;

    // POPUP SCREEN
    int TextBoxY = 380;

    int TextBoxAX = 50;
    int TextBoxAY = TextBoxY;

    int TextBoxBX = 330;
    int TextBoxBY = TextBoxY;

    int TextBoxCX = 630;
    int TextBoxCY = TextBoxY;

    int TextBoxDX = 960;
    int TextBoxDY = TextBoxY;

}

class Question
{
    String question;
    String first, second, third, fourth;
    String correctAnswer;
}