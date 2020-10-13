package hku.hkucs.mcrpg;
import java.util.ArrayList;

public class questionStruct {
    public String theme;
    public int difficulty; //1-5
    public String question;
    ArrayList<String> ansBank = new ArrayList<String>();
    public int ansPosition;
    public void setter(String theme, int difficulty, String questionContent, String option1, String option2, String option3, String option4, int ans) {
        this.theme = theme;
        this.difficulty = difficulty;
        this.question = questionContent;
        this.ansBank.add(option1);
        this.ansBank.add(option2);
        this.ansBank.add(option3);
        this.ansBank.add(option4);
        this.ansPosition = ans;
    }
    public String getTheme() {
        return this.theme;
    }
    public int getDifficulty() {
        return this.difficulty;
    }
    public String getQuestion() {
        return this.question;
    }
    public ArrayList<String> getAnsBank() {
        return this.ansBank;
    }
    public int getAnsPosition() {
        return this.ansPosition;
    }
}
