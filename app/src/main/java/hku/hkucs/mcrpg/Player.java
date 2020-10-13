package hku.hkucs.mcrpg;

import android.os.CountDownTimer;

public class Player {

    private int health = 100;
    public int getHealth() {
        return  health;
    }
    private  float maximumAttackDamage = 50f;
    private CountDownTimer countDownTimer;
    private long timeLeft = 30000; //That is 30 sec
    public long getTimeLeft() {
        return timeLeft;
    }
    private long timeAvailablePerQuestion = 30000; //That is 30 sec
    private long additionTime = 0;
    public boolean heal;


    private int[] skillsOriginalCD = new int[]{8,10,6,4};
    public int[] skillsCoolDown = {skillsOriginalCD[0], skillsOriginalCD[1], skillsOriginalCD[2], skillsOriginalCD[3]};

    public void underAttack(int damage) {
            health -= damage;
            System.out.println("Health = " + health);
            if (health <= 0) {
                //TODO: do sth to notify the player
                System.out.println("You are dead");
                MainActivity.getInstance().GameOver();

            }
    }

    //Attack will only be called when the player answer the correct answer
    public int Attack() {
        int damageDealt = Math.round(maximumAttackDamage * timeLeft/timeAvailablePerQuestion);
        System.out.println("Damage Dealt: "+ damageDealt);
        if (heal) {
            heal(damageDealt);
        }
        return damageDealt;
    }

    public void heal(float healAmount) {
        health+=healAmount;
        heal = false;
    }

    public void playerStartTurn() {
        resetTimer();
    }

    public void ResetCoolDown(int skillsIndex) {
        skillsCoolDown[skillsIndex] = skillsOriginalCD[skillsIndex];
    }

    public void DelayPlayerCD() {
        for (int i = 0; i< skillsCoolDown.length; i++) {
            skillsCoolDown[i]+=4;
        }
    }
    public void playerEndTurn() {

        for (int i = 0; i < 4; i++) {
            if (skillsCoolDown[i] > 0) {
                skillsCoolDown[i]--;
            }
        }
//Add back timer later
       stopTimer();
        heal = false; //To reset the effect if player use heal this turn;
        additionTime = 0;
        timeAvailablePerQuestion = 30000; //To reset the effect if player use lengthen answer time this turn;
    }

    public void increaseAvailableTime() {
       stopTimer();
       resetTimer();
        System.out.println("updated skill3 cd");
    }

    public void startTimer() {
        countDownTimer = new CountDownTimer(timeLeft,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeft = additionTime +  millisUntilFinished;
//                System.out.println("TimeLeft:" + timeLeft);

            }

            @Override
            public void onFinish() {
//                System.out.println("Time's up!");
                MainActivity.getInstance().EndTurn(0);
            }
        }.start();
    }

    public void stopTimer() {
        countDownTimer.cancel();

    }

    public void resetTimer() {
        timeLeft = timeAvailablePerQuestion;
        countDownTimer.start();
    }





}
