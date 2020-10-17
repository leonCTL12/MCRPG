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
        question2.setter("Mathematics", 5, "What is the answer of 1+2+3+...+48+49+50?", "1000", "1175", "1200", "1275", 3);
        question3.setter("English", 1, "Where does the world \"Clichè\" come from?", "Spain", "France", "Egypt", "America", 1);
        question4.setter("Geography", 3, "What is the name of the second highest mountain?", "Mount Everest", "K2", "Cho Oyu", "Lhotse", 1);
        question5.setter("Biology", 2, "What is the range length of male blue whale penis?", "1 to 2 meters", "2 to 3 meters", "3 to 4 meters", "30cm", 1);
        question6.setter("Tricky Question", 1, "What is always coming, but never arrives?", "Winter", "Hi-bye friend", "Tomorrow", "The tortoise from Zeno's paradox", 2);
        question7.setter("Philosophy", 4, "Who is the authur of Discours de la méthode？", "Friedrich Nietzsche", "Aristotle", "Socrates", "René Descartes", 3);
        question8.setter("Computer Science", 5, "What is the time complexity of breadth-first search of a graph?", "O(V)", "O(E)", "O(V+E)", "O(V-E)", 2);
        question9.setter("Geography", 2, "What country does the city \"Fucking\" locate at?", "Bulgaria", "Australia", "Austria", "Argentina", 2);
        question10.setter("English", 2, "What work is not written by William Shakespeare?", "Dracula", "Othello", "Harmlet", "Macbeth", 0);
        question11.setter("Japanese",3,"What is the romaji conversion of this Japanese hiragana \"あ\"?", "a", "i", "u" , "o", 0);
        question12.setter("Music", 4, "Who composed the piece \"Winter Wind\" ?", "Tchaikovsky" , "Chopin", "Haydn", "Bach", 1 );
        question13.setter("Spanish", 1, "What does \"Uno\" mean in Spanish?", "4", "2" ,"3" ,"1", 3 );
        question14.setter("Geography", 3, "What is the name of the smallest country in the world?", "Monaco", "Nauru", "Tuvalu","Vatican City",3);
        question15.setter("Biology", 3, "How much water does a human have in their brain approximately?", "90%" , "50%" , "60%" , "70%" , 3);  
        question16.setter("Animal Studies", 1, "What is the heart beat rate of a sloth?" ,"150bpm", "10bpm", "80bpm" , "120bpm", 2);    
        question17.setter("Chemistry", 5, "Who invented the first full functional mass spectrometer?", "Robert Bunson", "Michael Faraday", "Francis William Aston", "Ernest Rutherford" , 2);  
        question18.setter("Physics", 5, "At what speed does the Earth rotate itself at Earth's equator?", "2000 miles/h", "3000 miles/h" , "1000 miles/h", "4000 miles/h", 2);
        question19.setter("Film Studies", 1, "Who is the director of the famous movie \"Inception\"?", "James Cameron", "Christopher Nolan", "The Wachowskis", "Peter Jackson", 1);
        question20.setter("History", 2, "When did the World War II end?", "1940", "1950" , "1935" ,"1945",3);
        question21.setter("Music", 1, "In Ed Sheeran album \"No.6 Collaboration\", who did he collaborate with in the song \"Beautiful People\"?", "Justin Bieber", "Khalid", "Cardi B", "Bruno Mars", 1);    
        question22.setter("Mathematics", 2, "What is the correct answer of pi in 6 deciml places?", "3.14159266", "3.14159265", "3.14159263", "3.14159264", 1);
        question23.setter("English", 1, "What is the meaning of the word \"hangry\"?", "handing angrily" , "handling angrily" , "hungry angrily" ,"hanging angrily", 2);
        question24.setter("French", 1, "What does the word \"Oui\" mean in French?", "Yes", "No", "He", "She", 0);
        question25.setter("Geography", 2, "What is the name of the shortest river in the world?" , "Roe River", "D River", "Amur" , "Amon", 0);
        question26.setter("Animal Studies", 3 , "What is the name of the smallest owl?", "Houdini", "Elf Owl", "Barn Owl", "Snowy Owl", 1 );
        question27.setter("Gaming", 3, "When did the most famous online game \"League of Legends\" be released?", "2010" , "2012" ,"2009", "2013", 2);
        question28.setter("Youtuber", 1, "What is the name of the most subscribed youtube channel in 2018?", "T-Series", "MrBeast" , "Vanossgaming", "Pewdiepie", 3);
        question29.setter("Technology", 3, "What autonomous driving level has Tesla achieved in 2019?", "1", "2", "3" ,"4", 1 );
        question30.setter("Botany", 1 , "How much water is in Apple(fruit)?" , "50%" , "25%" , "75%" , "10", 1);
            
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
        questionBank.add(question11);
        questionBank.add(question12);
        questionBank.add(question13);
        questionBank.add(question14);
        questionBank.add(question15);
        questionBank.add(question16);
        questionBank.add(question17);
        questionBank.add(question18);
        questionBank.add(question19);
        questionBank.add(question20);
        questionBank.add(question21);
        questionBank.add(question22);
        questionBank.add(question23);
        questionBank.add(question24);
        questionBank.add(question25);
        questionBank.add(question26);
        questionBank.add(question27);
        questionBank.add(question28);
        questionBank.add(question29);
        questionBank.add(question30);
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


