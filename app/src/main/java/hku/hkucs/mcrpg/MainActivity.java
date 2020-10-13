package hku.hkucs.mcrpg;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.net.Inet4Address;

public class MainActivity extends AppCompatActivity { //Leon: this script will act as something like game manager in Unity

    private static MainActivity instance;

    public  static  MainActivity getInstance() {
        return  instance;
    }

    //Monster
    MonsterSet monsterSet;
    Monster monster;
    ProgressBar monsterHealthBar;
    TextView stage;
    TextView monsterName;
    TextView ability1_cd;
    TextView ability2_cd;
    TextView ability3_cd;
    TextView ability4_cd;
    TextView ability1_name;
    TextView ability2_name;
    TextView ability3_name;
    TextView ability4_name;
    ImageView monsterImage;
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

    //Question
    TextView questionLocation;
    questionBank questionSet;
    questionStruct currentQuestion;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        instance = this;

        // initialize
        //Monster
        monsterSet = new MonsterSet();
        monsterHealthBar = findViewById(R.id.progressBar);
        stage = findViewById(R.id.textView_stage);
        monsterName = findViewById(R.id.textView_monsterName);
        ability1_cd = findViewById(R.id.textView_ability1_cd);
        ability2_cd = findViewById(R.id.textView_ability2_cd);
        ability3_cd = findViewById(R.id.textView_ability3_cd);
        ability4_cd = findViewById(R.id.textView_ability4_cd);
        ability1_name = findViewById(R.id.textView_ability1_name);
        ability2_name = findViewById(R.id.textView_ability2_name);
        ability3_name = findViewById(R.id.textView_ability3_name);
        ability4_name = findViewById(R.id.textView_ability4_name);
        monsterImage = findViewById(R.id.imageView_monster);
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

        timer = findViewById(R.id.Timer);
//        timerThread = new Thread() {
//
//            @Override
//            public void run() {
//                try {
//                    while (!timerThread.isInterrupted()) {
//                        Thread.sleep(1000);
//                        runOnUiThread(new Runnable() {
//                            @Override
//                            public void run() {
//                                // update TextView here!
//                                timer.setText(String.valueOf(player.getTimeLeft()));
//                            }
//                        });
//                    }
//                } catch (InterruptedException e) {
//                }
//            }
//        };
//        timerThread.start();

        //Question
        questionSet = new questionBank();
        questionSet.buildingQuestionSet();// invoke the question questionBank, the question set array has been shuffled and ready to be drawn
        questionLocation = findViewById(R.id.textView_Question);



        //general
        //Game Initialization
        player.startTimer();
        UpdateMonster();
        newTurn();

        //Player
        optionA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO: call question evaluate answer function
                System.out.println("Clicked OptionA");


                if(questionSet.evaluation(0, currentQuestion)) {

                    EndTurn(player.Attack());
                } else {
                    EndTurn(0);
                }
            }
        });
        optionB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO: call question evaluate answer function
                System.out.println("Clicked OptionB");
                if(questionSet.evaluation(1, currentQuestion)) {

                    EndTurn(player.Attack());
                } else {
                    EndTurn(0);
                }
            }
        });
        optionC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO: call question evaluate answer function
                System.out.println("Clicked OptionC");
                if(questionSet.evaluation(2, currentQuestion)) {

                    EndTurn(player.Attack());
                } else {
                    EndTurn(0);
                }
            }
        });
        optionD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO: call question evaluate answer function
                System.out.println("Clicked OptionD");
                if(questionSet.evaluation(3, currentQuestion)) {

                    EndTurn(player.Attack());
                } else {
                    EndTurn(0);
                }

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


//Leon: Can we implement sth like unity's update by multi-threading? Will this cause any performance problem?
        updateThread = new Thread() {

            @Override
            public void run() {
                try {
                    while (!updateThread.isInterrupted()) {
                        Thread.sleep(10);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                // update TextView here!
                                UpdateUI();
                            }
                        });
                    }
                } catch (InterruptedException e) {
                }
            }
        };
        updateThread.start();


    }



    public void EndTurn(int damage) {
        monster.underAttack(damage);
        if (monster.isDead()) {
            monsterSet.defected();
            UpdateMonster();
        }
        else {
            monster.endTurn();
            CheckAbilityCasting();
        }
        monster.setOffFire();
        player.playerEndTurn();
        newTurn();
    }

    public void UpdateUI() {
        //Monster
        monsterHealthBar.setProgress(monster.getHealth());
        ability1_cd.setText("CD " + monster.getAbilityCD(0));
        ability2_cd.setText("CD " + monster.getAbilityCD(1));
        ability3_cd.setText("CD " + monster.getAbilityCD(2));
        ability4_cd.setText("CD " + monster.getAbilityCD(3));
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

        //Timer
        timer.setText(String.valueOf(Math.round(player.getTimeLeft()/1000)));
    }

    public void RenderNewQuestion() {
        currentQuestion = questionSet.randomDrawQuestion(); //draw the first question
        questionLocation.setText(currentQuestion.getQuestion());
        optionA.setText(currentQuestion.getAnsBank().get(0));
        optionB.setText(currentQuestion.getAnsBank().get(1));
        optionC.setText(currentQuestion.getAnsBank().get(2));
        optionD.setText(currentQuestion.getAnsBank().get(3));
    }
    public void newTurn() {
        RenderNewQuestion();
        player.playerStartTurn();
    }

    //  Called when every answer is made, either right or wrong
    //  Check if any ability is needed to cast
    public void CheckAbilityCasting() {
        for (int i = 0; i < 4; i++) {
            if (monster.getAbilityCast(i)) {
                switch (monster.getAbilityID(i)) {
                    case -1:
                        System.out.println("Monster Debug: Null Ability");
                        break;
                    case 0:
                        player.underAttack(monster.getDamage());
                        System.out.println("Monster Debug: Normal Attack.");
                        break;
                    case 1:
                        // TODO: call relative function
                        System.out.println("Monster Debug: Decreasing answering time.");
                        break;
                    case 2:
                        // TODO: call relative function
                        System.out.println("Monster Debug: Increasing question difficulty.");
                        break;
                    case 3:
                        // TODO: call relative function
                        System.out.println("Monster Debug: Increasing player skill CD.");
                        break;
                    case 4:
                        // TODO: call relative function
                        System.out.println("Monster Debug: Scrambling Answer.");
                        break;
                    case 5:
                        // TODO: call relative function
                        System.out.println("Monster Debug: Locking Player Skills.");
                        break;
                    case 6:
                        // TODO: call relative function
                        System.out.println("Monster Debug: Double-edge Sword.");
                        break;
                    default:
                        System.out.println("Monster Debug: Error abilityID.");
                }
                monster.setAbilityCast(i, false);
            }
        }
    }

    //  Update UI when new monster is spawned
    public void UpdateMonster() {
        monster = monsterSet.getCurrentMonster();
        stage.setText("Stage " + String.valueOf(monsterSet.getCurrentStage()));
        monsterName.setText(monster.getName());
        monsterImage.setImageResource(getResources().getIdentifier(monster.getImagePath(), "drawable", getPackageName()));
        ability1_name.setText(monster.getAbilityName(0));
        ability2_name.setText(monster.getAbilityName(1));
        ability3_name.setText(monster.getAbilityName(2));
        ability4_name.setText(monster.getAbilityName(3));
        if (monster.getAbilityID(1) == -1) {
            ability2_name.setVisibility(View.INVISIBLE);
            ability2_cd.setVisibility(View.INVISIBLE);
        }
        else {
            ability2_name.setVisibility(View.VISIBLE);
            ability2_cd.setVisibility(View.VISIBLE);
        }
        if (monster.getAbilityID(2) == -1) {
            ability3_name.setVisibility(View.INVISIBLE);
            ability3_cd.setVisibility(View.INVISIBLE);
        }
        else {
            ability3_name.setVisibility(View.VISIBLE);
            ability3_cd.setVisibility(View.VISIBLE);
        }if (monster.getAbilityID(3) == -1) {
            ability4_name.setVisibility(View.INVISIBLE);
            ability4_cd.setVisibility(View.INVISIBLE);
        }
        else {
            ability4_name.setVisibility(View.VISIBLE);
            ability4_cd.setVisibility(View.VISIBLE);
        }
    }

    public void GameOver() {
         startActivity(new Intent(MainActivity.this, DealthPopUp.class));
    }
}