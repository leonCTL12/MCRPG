package hku.hkucs.mcrpg;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity { //Leon: this script will act as something like game manager in Unity

    //Monster
    monster monster;
    ProgressBar monsterHealthBar;
    TextView ability1_cd;
    TextView ability2_cd;
    TextView ability3_cd;
    TextView ability4_cd;
    ImageView effect_fire1;
    ImageView effect_fire2;

    //Player
    Player player;
    ProgressBar playerHealthBar;
    Button optionA;
    Button optionB;
    Button optionC;
    Button optionD;
    ImageButton skill1;
    ImageButton skill2;
    ImageButton skill3;
    ImageButton skill4;
    TextView skill1CD;
    TextView skill2CD;
    TextView skill3CD;
    TextView skill4CD;

    //Timer
    TextView timer;
    Thread timerThread;
    Thread updateThread;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // initialize
        //Monster
        monster = new monster();
        monsterHealthBar = findViewById(R.id.progressBar);
        ability1_cd = findViewById(R.id.textView_ability1_cd);
        ability2_cd = findViewById(R.id.textView_ability2_cd);
        ability3_cd = findViewById(R.id.textView_ability3_cd);
        ability4_cd = findViewById(R.id.textView_ability4_cd);
        effect_fire1 = findViewById(R.id.imageView_effect_fire1);
        effect_fire2 = findViewById(R.id.imageView_effect_fire2);

        //Player
        player = new Player();
        playerHealthBar = findViewById(R.id.PlayerHP);
        optionA = findViewById(R.id.OptionA);
        optionB = findViewById(R.id.OptionB);
        optionC = findViewById(R.id.OptionC);
        optionD = findViewById(R.id.OptionD);
        skill1 = findViewById(R.id.Skill1);
        skill2 = findViewById(R.id.Skill2);
        skill3 = findViewById(R.id.Skill3);
        skill4 = findViewById(R.id.Skill4);
        skill1CD = findViewById(R.id.Skill1CD);
        skill2CD = findViewById(R.id.Skill2CD);
        skill3CD = findViewById(R.id.Skill3CD);
        skill4CD = findViewById(R.id.Skill4CD);

        //Timer
        player.startTimer(); //testing timer
        timer = findViewById(R.id.Timer);
        timerThread = new Thread() {

            @Override
            public void run() {
                try {
                    while (!timerThread.isInterrupted()) {
                        Thread.sleep(1000);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                // update TextView here!
                                timer.setText(String.valueOf(player.getTimeLeft()));
                            }
                        });
                    }
                } catch (InterruptedException e) {
                }
            }
        };
        timerThread.start();

        //general
        //Leon: Can we implement sth like unity's update by multi-threading? Will this cause any performance problem?
        updateThread = new Thread() {

            @Override
            public void run() {
                try {
                    while (!updateThread.isInterrupted()) {
                        Thread.sleep(100);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                // update TextView here!
                                System.out.println("I am still running");
                                UpdateUI();
                            }
                        });
                    }
                } catch (InterruptedException e) {
                }
            }
        };
       updateThread.start();


        // button for testing

        //Monster
        Button button_attack = findViewById(R.id.button);
        Button button_reset = findViewById(R.id.button2);
        Button button_onFire = findViewById(R.id.button3);
        Button button_offFire = findViewById(R.id.button4);

        button_attack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                monster.attack(10);
                monster.endTurn();
//                UpdateUI();
            }
        });

        button_reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                monster.initialize();
//                UpdateUI();
            }
        });

        button_onFire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                monster.setOnFire();

                //Leon: 借來用下 test 野
                player.playerStartTurn();

            }
        });

        button_offFire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                monster.setOffFire();

                //Leon: 借來用下 test 野
                player.playerEndTurn();


            }
        });


        //Player
        optionA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO: call question evaluate answer function
                System.out.println("Clicked OptionA");
            }
        });
        optionB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO: call question evaluate answer function
                System.out.println("Clicked OptionB");
            }
        });
        optionC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO: call question evaluate answer function
                System.out.println("Clicked OptionC");
            }
        });
        optionD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO: call question evaluate answer function
                System.out.println("Clicked OptionD");
            }
        });
        skill1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("Skill1");
            }
        });
        skill2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("Skill2");
                //TODO: call monster add all cool down function
            }
        });
        skill3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                player.increaseAvailableTime();
            }
        });
        skill4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("Skill4");
            }
        });






    }

    public void UpdateUI() {
        //Monster
        monsterHealthBar.setProgress(monster.getHealth());
        ability1_cd.setText("CD " + monster.getAbility_1_cd());
        ability2_cd.setText("CD " + monster.getAbility_2_cd());
        ability3_cd.setText("CD " + monster.getAbility_3_cd());
        ability4_cd.setText("CD " + monster.getAbility_4_cd());
        if (monster.getFireStatus()) {
            effect_fire1.setVisibility(View.VISIBLE);
            effect_fire2.setVisibility(View.VISIBLE);
        }
        else {
            effect_fire1.setVisibility(View.INVISIBLE);
            effect_fire2.setVisibility(View.INVISIBLE);
        }

        //Player
        float alpha = 0f;
        playerHealthBar.setProgress(player.getHealth());

        skill1CD.setText(String.valueOf(player.skillsCoolDown[0]));
        alpha = (player.skillsCoolDown[0] == 0) ? 0f: 0.8f;
        skill1CD.setAlpha(alpha);
        skill1CD.setClickable(player.skillsCoolDown[0]!=0);

        skill2CD.setText(String.valueOf(player.skillsCoolDown[1]));
        alpha = (player.skillsCoolDown[1] == 0) ? 0f: 0.8f;
        skill2CD.setAlpha(alpha);
        skill2CD.setClickable(player.skillsCoolDown[1]!=0);

        skill3CD.setText(String.valueOf(player.skillsCoolDown[2]));
        alpha = (player.skillsCoolDown[2] == 0) ? 0f: 0.8f;
        skill3CD.setAlpha(alpha);
        skill3CD.setClickable(player.skillsCoolDown[2]!=0);

        skill4CD.setText(String.valueOf(player.skillsCoolDown[3]));
        alpha = (player.skillsCoolDown[3] == 0) ? 0f: 0.8f;
        skill4CD.setAlpha(alpha);
        skill4CD.setClickable(player.skillsCoolDown[3]!=0);




    }
}