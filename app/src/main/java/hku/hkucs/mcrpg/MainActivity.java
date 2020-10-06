package hku.hkucs.mcrpg;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity { //Leon: this script will act as something like game manager in Unity

    Player player;
    monster monster;
    ProgressBar monsterHealthBar;
    TextView ability1_cd;
    TextView ability2_cd;
    TextView ability3_cd;
    TextView ability4_cd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // initialize
        monster = new monster();
        monsterHealthBar = findViewById(R.id.progressBar);
        ability1_cd = findViewById(R.id.textView_ability1_cd);
        ability2_cd = findViewById(R.id.textView_ability2_cd);
        ability3_cd = findViewById(R.id.textView_ability3_cd);
        ability4_cd = findViewById(R.id.textView_ability4_cd);


        // button for testing
        Button button_attack = findViewById(R.id.button);
        Button button_reset = findViewById(R.id.button2);

        button_attack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                monster.attack(10);
                monster.endTurn();
                UpdateUI();
            }
        });

        button_reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                monster.initialize();
                UpdateUI();
            }
        });

        UpdateUI();
    }

    public void UpdateUI() {
        monsterHealthBar.setProgress(monster.getHealth());
        ability1_cd.setText("CD " + monster.getAbility_1_cd());
        ability2_cd.setText("CD " + monster.getAbility_2_cd());
        ability3_cd.setText("CD " + monster.getAbility_3_cd());
        ability4_cd.setText("CD " + monster.getAbility_4_cd());
    }
}