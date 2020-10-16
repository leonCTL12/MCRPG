package hku.hkucs.mcrpg;
import java.util.ArrayList;
import java.util.Random;
import java.util.*;

public class questionBank {
    ArrayList<questionStruct> questionBank = new ArrayList<questionStruct>();
    ArrayList<questionStruct> hardQuestionBank = new ArrayList<questionStruct>();
    int generalPosition = 0;
    int hardQuestionPosition = 0;
    questionStruct question1 = new questionStruct();
    questionStruct question2 = new questionStruct();
    questionStruct question3 = new questionStruct();
    questionStruct question4 = new questionStruct();
    questionStruct question5 = new questionStruct();
    questionStruct question6 = new questionStruct();
    questionStruct question7 = new questionStruct();
    questionStruct question8 = new questionStruct();
    questionStruct question9 = new questionStruct();
    questionStruct question10 = new questionStruct();


    //question bank, hardcoded
    public void infoBase() {
        question1.setter("Chemistry", 1, "What is the atomic number of Aluminium", "13", "14", "15", "16", 0);
        question2.setter("Mathematics", 2, "What is the answer of 1+2+3+...+48+49+50?", "1000", "1175", "1200", "1275", 3);
        question3.setter("English", 1, "Where does the world \"Clichè\" come from?", "Spain", "France", "Egypt", "America", 1);
        question4.setter("Geography", 3, "What is the name of the second highest mountain?", "Mount Everest", "K2", "Cho Oyu", "Lhotse", 1);
        question5.setter("Biology", 2, "What is the range length of male blue whale penis?", "1 to 2 meters", "2 to 3 meters", "3 to 4 meters", "30cm", 1);
        question6.setter("Tricky Question", 1, "What is always coming, but never arrives?", "Winter", "Hi-bye friend", "Tomorrow", "The tortoise from Zeno's paradox", 2);
        question7.setter("Philosophy", 4, "Who is the authur of Discours de la méthode？", "Friedrich Nietzsche", "Aristotle", "Socrates", "René Descartes", 3);
        question8.setter("Computer Science", 5, "What is the time complexity of breadth-first search of a graph?", "O(V)", "O(E)", "O(V+E)", "O(V-E)", 2);
        question9.setter("Geography", 2, "What country does the city \"Fucking\" locate at?", "Bulgaria", "Australia", "Austria", "Argentina", 2);
        question10.setter("English", 2, "What work is not written by William Shakespeare?", "Dracula", "Othello", "Harmlet", "Macbeth", 0);
        //for further improvement, can build another arraylist of problems for hardness level 5 instead of mixing it in general questionSet
        questionBank.add(question1);
        questionBank.add(question2);
        questionBank.add(question3);
        questionBank.add(question4);
        questionBank.add(question5);
        questionBank.add(question6);
        questionBank.add(question7);
        questionBank.add(question8);
        questionBank.add(question9);
        questionBank.add(question10);
        System.out.println("Q bank size in info base:" + questionBank.size());
    }

    //finding the question with difficulty 5
    public void constructingHardnessQuestion(){
        for (int i = 0; i < questionBank.size(); i++) {
            if (questionBank.get(i).getDifficulty() == 5) {
                questionBank.remove(questionBank.get(i)); //Leon: I think the normal question bank should not contain hard question
                hardQuestionBank.add(questionBank.get(i));
            }
        }
    }
    //shuffling the questionSet
    public void shuffling() {
        constructingHardnessQuestion();
        Collections.shuffle(questionBank);

        //For testing
        System.out.println("Q Bank size = "+questionBank.size());
        for (int i = 0; i< questionBank.size(); i++) {
            System.out.println(questionBank.get(i).question);
        }

        Collections.shuffle(hardQuestionBank);
    }
    //checking if the answer is correct
    public boolean evaluation(int option, questionStruct QuestionInFunction) {
        if (option == QuestionInFunction.getAnsPosition()) {
            System.out.println(("Correct!"));
            return true;
        } else {
            System.out.println(("Incorrect!"));
            return false;
        }


    }
    //after shuffling the questionSet, everytime we call this method, it will return the next question, so it doesnt repeat
    public questionStruct randomDrawQuestion(){
        System.out.println("drawn new Q; General Pos = " + generalPosition);

        generalPosition++;
        if (generalPosition >= questionBank.size()-1) {
            generalPosition = 1;
        }
        return questionBank.get(generalPosition-1);
    }
    //same as drawing question from general questionSet
    public questionStruct drawHardQuestion() {
        hardQuestionPosition++;
        if(hardQuestionPosition >= hardQuestionBank.size() - 1) {
            hardQuestionPosition = 1;
        }
        return  hardQuestionBank.get(hardQuestionPosition-1);
    }
    //invoke this class
    public void buildingQuestionSet() {
        System.out.println("Building Q Set");
        infoBase();
        shuffling();
    }
}


