package hku.hkucs.mcrpg;
import java.util.ArrayList;

public class questionstruct {
    public String theme;
    public int difficulty; //1-5
    public String question;
    ArrayList<String> ansbank = new ArrayList<String>();
    public int ansposition;
    public void setter(String a, int b, String c, String d1, String d2, String d3, String d4, int e) {
        this.theme = a;
        this.difficulty = b;
        this.question = c;
        this.ansbank.add(d1);
        this.ansbank.add(d2);
        this.ansbank.add(d3);
        this.ansbank.add(d4);
        this.ansposition = e;



    }
    public String gettheme() {
        return this.theme;
    }
    public int getdifficulty() {
        return this.difficulty;
    }
    public String getquestion() {
        return this.question;
    }
    public ArrayList<String> getansbank() {
        return this.ansbank;
    }
    public int getansposition() {
        return this.ansposition;
    }
}
