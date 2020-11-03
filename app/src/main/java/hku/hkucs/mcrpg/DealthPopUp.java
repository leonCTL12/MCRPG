package hku.hkucs.mcrpg;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class DealthPopUp extends Activity {

    TextView questionCount;
    TextView correctCount;
    TextView correctPercentage;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.deathpopupwindow);
        questionCount = findViewById(R.id.questionCount);
        correctCount = findViewById(R.id.correctCount);
        correctPercentage = findViewById(R.id.correctRate);
        int QCount = MainActivity.getInstance().questionCount;
        String questionCountText = "You have answered " + QCount + " questions";
        questionCount.setText(questionCountText);
        int CorCount = MainActivity.getInstance().correctCount;
        String correctCountText = "Question correctly answered: " + CorCount;
        correctCount.setText(correctCountText);
        double percentage = (CorCount * 1.0)/QCount*100.0 ;
        double roundPercentage = Math.round(percentage * 10) / 10.0;
        String correctRateText = "Correct Percentage: " + roundPercentage + "%";
        correctPercentage.setText(correctRateText);



    }
}
