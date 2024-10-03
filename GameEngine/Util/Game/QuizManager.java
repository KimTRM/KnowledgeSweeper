package GameEngine.Util.Game;

import GameEngine.Graphics.AssetManager;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.List;

public class QuizManager {
    AssetManager assetManager;
    GameBoard gameBoard;

    // ----- QUESTION RANDOMIZER -----
    public String Question;
    public String A, B, C, D;
    public String correctAnswer;
    public boolean Science, History, Math;

    File[] file = new File[5];
    List<Question> questionList = new ArrayList<>();
    public int x;

    // ----- ANSWER CHECKER -----
    public boolean AnswerCorrect;
    public boolean AnswerWrong;

    // ----- CLICK HANDLER -----
    public boolean ansA;
    public boolean ansB;
    public boolean ansC;
    public boolean ansD;
    public boolean Confirm;

    public QuizManager(AssetManager assetManager, GameBoard gameBoard)
    {
        this.assetManager = assetManager;
        this.gameBoard = gameBoard;
    }

    public void ChangeSubject(String Subject)
    {
        switch (Subject)
        {
            case "Science":
                if (Science && !History && !Math)
                {
                    file[0] = new File("GameEngine/Graphics/res/files/Subjects/FILEQUESTIONSCIENCE.txt");
                    getQuestion(0);
                }
                break;
            case "History":
                if (History && !Science && !Math)
                {
                    file[1] = new File("GameEngine/Graphics/res/files/Subjects/FILEQUESTIONHISTORY.txt");
                    getQuestion(1);
                }
                break;
            case "Math":
                if (Math && !Science && !History)
                {
                    file[2] = new File("GameEngine/Graphics/res/files/Subjects/FILEQUESTIONSMATH.txt");
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

    public void random()
    {
        Collections.shuffle(questionList);
        // Ask questions and get user's answers
        for (Question question : questionList)
        {
            Question = question.question;
            A = question.first;
            B = question.second;
            C = question.third;
            D = question.fourth;
            correctAnswer = question.correctAnswer;
        }
    }

    public String getAnswer() {

        if (ansA) {
            return "A";
        } else if (ansB) {
            return "B";
        } else if (ansC) {
            return "C";
        } else if (ansD) {
            return "D";
        }

        return null;
    }

    public void checkAnswer()
    {
        String userAnswer = getAnswer();

        if (userAnswer != null)
        {
            if (userAnswer.equals(correctAnswer))
            {
                AnswerCorrect = true;
                AnswerWrong = false;
                assetManager.playSE(2);

            }
            else
            {
                AnswerCorrect = false;
                AnswerWrong = true;
                assetManager.playSE(3);
            }
        }

        if (seconds == 0)
        {
            AnswerCorrect = false;
            AnswerWrong = true;
            assetManager.playSE(3);
        }
    }

    public void update()
    {
        inQA = assetManager.TextBoxCollision(TextBoxAX, TextBoxAY, 100, 50, 300, 250, false, "A");
        inQB = assetManager.TextBoxCollision(TextBoxBX, TextBoxBY, 100, 50, 300, 250, false, "B");
        inQC = assetManager.TextBoxCollision(TextBoxCX, TextBoxCY, 100, 50, 300, 250, false, "C");
        inQD = assetManager.TextBoxCollision(TextBoxDX, TextBoxDY, 100, 50, 300, 250, false, "D");
    }

    public void input()
    {
        if (inQA) {
            ansA = true;
            ansB = false;
            ansC = false;
            ansD = false;
            Confirm = true;
        }

        if (inQB) {
            ansA = false;
            ansB = true;
            ansC = false;
            ansD = false;
            Confirm = true;
        }

        if (inQC) {
            ansA = false;
            ansB = false;
            ansC = true;
            ansD = false;
            Confirm = true;
        }

        if (inQD) {
            ansA = false;
            ansB = false;
            ansC = false;
            ansD = true;
            Confirm = true;
        }

        if (Confirm) {
            checkAnswer();
            random();

        }
    }

    public void render(Graphics2D g)
    {
        g.drawImage(AssetManager.Shade, 0, 0, assetManager.getScreenWidth(), assetManager.getScreenHeight(), null);
        g.drawImage(AssetManager.Shade, 0, 0, assetManager.getScreenWidth(), assetManager.getScreenHeight(), null);

        g.drawImage(AssetManager.Leaderboard, 250, 10, 800, 350, null);

        assetManager.TextBox(TextBoxAX, TextBoxAY, 300, 150, g);
        assetManager.TextBox(TextBoxBX, TextBoxBY, 300, 150, g);
        assetManager.TextBox(TextBoxCX, TextBoxCY, 300, 150, g);
        assetManager.TextBox(TextBoxDX, TextBoxDY, 300, 150, g);

        g.setColor(Color.BLACK);
        int Qy = 180;
        int Qx = 320;
        assetManager.PrintTexts(Question, Qx, Qy, 48, 55, false, g);

        assetManager.PrintTexts(A, TextBoxAX + 25, TextBoxAY + 80, 48, 30, false, g);
        assetManager.PrintTexts(B, TextBoxBX + 25, TextBoxBY + 80, 48, 30, false, g);
        assetManager.PrintTexts(C, TextBoxCX + 25, TextBoxCY + 80, 48, 30, false, g);
        assetManager.PrintTexts(D, TextBoxDX + 25, TextBoxDY + 80, 48, 30, false, g);

        // -- HOVER (A,B,C,D) --
        if (inQA)
        {
            g.drawImage(AssetManager.Select1, TextBoxAX, TextBoxAY, 300, 150, null);
        }
        if (inQB)
        {
            g.drawImage(AssetManager.Select1, TextBoxBX, TextBoxBY, 300, 150, null);
        }
        if (inQC)
        {
            g.drawImage(AssetManager.Select1, TextBoxCX, TextBoxCY, 300, 150, null);
        }
        if (inQD)
        {
            g.drawImage(AssetManager.Select1, TextBoxDX, TextBoxDY, 300, 150, null);
        }

        if (seconds <= 10)
        {
            g.setColor(Color.RED);
        }
        else
        {
            g.setColor(new Color(73, 29, 0));
        }

//        g.drawImage(AssetManager.Timer, Qx - 20, Qy - 120, 80, 80, null);
        assetManager.PrintTexts(String.valueOf(seconds), Qx, Qy - 60, 48, 55, false, g);
    }

    public boolean Stoptimer;
    public int seconds;
    public void Timer()
    {
        Stoptimer = false;
        seconds = gameBoard.TimerSeconds;
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {

            @Override
            public void run()
            {
                if (seconds > 0) {
                    seconds--;
                }
                else {
                    timer.cancel(); // Stop the timer
                }

                if (Stoptimer) {
                    timer.cancel();
                }
            }
        };

        timer.scheduleAtFixedRate(task, 0, 1000); // Schedule task to run every second

    }

    boolean inQA, inQB, inQC, inQD;

    int Boxlenght = 300;
    int Boxwidth = 120;

    // POPUP SCREEN
    int TextBoxY = 380;

    int TextBoxAX = 320;
    int TextBoxAY = TextBoxY;

    int TextBoxBX = 320;
    int TextBoxBY = TextBoxY + 150;

    int TextBoxCX = 680;
    int TextBoxCY = TextBoxY;

    int TextBoxDX = 680;
    int TextBoxDY = TextBoxY + 150;

}

class Question
{
    String question;
    String first, second, third, fourth;
    String correctAnswer;
}