package hku.hkucs.mcrpg;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity { //Leon: this script will act as something like game manager in Unity

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
    TextView Questionlocation;
    questionbank questionset;
    questionstruct currquestion;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
        UpdateMonster();

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

        //Question
        questionset = new questionbank();
        questionset.buildingaquestionset();// invoke the question questionbank, the question set array has been shuffled and ready to be drawn
        currquestion = questionset.randomdrawquestion(); //draw the first question
        Questionlocation = findViewById(R.id.textView_Question);
        Questionlocation.setText(currquestion.getquestion());
        optionA.setText(currquestion.getansbank().get(0));
        optionB.setText(currquestion.getansbank().get(1));
        optionC.setText(currquestion.getansbank().get(2));
        optionD.setText(currquestion.getansbank().get(3));

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
        Button button_attack = findViewById(R.id.button);
        Button button_reset = findViewById(R.id.button2);
        Button button_onFire = findViewById(R.id.button3);
        Button button_offFire = findViewById(R.id.button4);

        button_attack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                monster.attack(10);
                if (monster.isDead()) {
                    monsterSet.defected();
                    UpdateMonster();
                }
                else {
                    monster.endTurn();
                    CheckAbilityCasting();
                }
//                UpdateUI();
            }
        });

        button_reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                monsterSet.initialize();
                UpdateMonster();
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
}