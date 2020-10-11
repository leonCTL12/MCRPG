package hku.hkucs.mcrpg;
import java.util.ArrayList;
import java.util.Random;
import java.util.*;

public class questionbank {
    ArrayList<questionstruct> questionbank = new ArrayList<questionstruct>();
    ArrayList<questionstruct> hardnessquestionbank = new ArrayList<questionstruct>();
    int generalposition = 0;
    int hardnessposition = 0;
    questionstruct question1 = new questionstruct();
    questionstruct question2 = new questionstruct();
    questionstruct question3 = new questionstruct();
    questionstruct question4 = new questionstruct();
    questionstruct question5 = new questionstruct();
    questionstruct question6 = new questionstruct();
    questionstruct question7 = new questionstruct();
    questionstruct question8 = new questionstruct();
    questionstruct question9 = new questionstruct();
    questionstruct question10 = new questionstruct();

    //question bank, hardcoded
    public void infobase() {
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
        //for further improvement, can build another arraylist of problems for hardness level 5 instead of mixing it in general questionset
        questionbank.add(question1);
        questionbank.add(question2);
        questionbank.add(question3);
        questionbank.add(question4);
        questionbank.add(question5);
        questionbank.add(question6);
        questionbank.add(question7);
        questionbank.add(question8);
        questionbank.add(question9);
        questionbank.add(question10);

    }

    //finding the question with difficulty 5
    public void constructinghardnessquestion(){
        for (int i = 0; i < questionbank.size(); i++) {
            if (questionbank.get(i).getdifficulty() == 5) {
                hardnessquestionbank.add(questionbank.get(i));
            }
        }
    }
    //shuffling the questionset
    public void shuffling() {
        infobase();
        constructinghardnessquestion();
        Collections.shuffle(questionbank);
        Collections.shuffle(hardnessquestionbank);
    }
    //checking if the answer is correct
    public boolean evaluation(int option, questionstruct questioninfunction) {
        if (option == questioninfunction.getansposition()) {
            //System.out.println(("Correct!"));
            return true;
        } else {
            //System.out.println(("Incorrect!"));
            return false;
        }
    }
    //after shuffling the questionset, everytime we call this method, it will return the next question, so it doesnt repeat
    public questionstruct randomdrawquestion(){

        generalposition++;
        return questionbank.get(generalposition-1);
    }
    //same as drawing question from general questionset
    public questionstruct samehardnessquestion() {

        hardnessposition++;
        return  hardnessquestionbank.get(hardnessposition-1);
    }
    //invoke this class
    public void buildingaquestionset() {
        infobase();
        shuffling();
    }
}


