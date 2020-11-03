package hku.hkucs.mcrpg;

import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Looper;

public class Player {

    public boolean doubleEdgeSword = false;
    public int skillLockRounds = 0;
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
    public long timeOffset = 0; //Set this to negative value to decrease player's answer time
    public boolean heal;


    private int[] skillsOriginalCD = new int[]{8,9,6,4};
    public int[] skillsCoolDown = {skillsOriginalCD[0], skillsOriginalCD[1], skillsOriginalCD[2], skillsOriginalCD[3]};

    public void underAttack(int damage) {
        MainActivity.getInstance().UnderAttackAnimation();
            health -= damage;
            if (health <= 0) {
                System.out.println("You are dead");
                MainActivity.getInstance().GameOver();

            }
    }

    //Attack will only be called when the player answer the correct answer
    public int Attack() {
        int damageDealt = Math.round(maximumAttackDamage * timeLeft/timeAvailablePerQuestion);
        if (heal) {
            heal(damageDealt);
        }
        if (doubleEdgeSword) {
            underAttack(damageDealt);
            doubleEdgeSword = false;
            MainActivity.getInstance().DoubleEdgeSwordIcon(false);
        }
        return damageDealt;

    }

    public void heal(float healAmount) {
        MainActivity.getInstance().HealAnimation();
        health+=healAmount;
        if (health > 100) {
            health = 100;
        }
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

        if (skillLockRounds > 0) {
            MainActivity.getInstance().updateSkillsLock();
            skillLockRounds--;
        }
//Add back timer later
       stopTimer();
        heal = false; //To reset the effect if player use heal this turn;
        timeAvailablePerQuestion = 30000; //To reset the effect if player use lengthen answer time this turn;
    }

    public void increaseAvailableTime() {
       stopTimer();
       resetTimer();
        System.out.println("updated skill3 cd");
    }

    public void startTimer(int countDownTime) {
        countDownTimer = new CountDownTimer(countDownTime,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
//
                timeLeft = timeOffset +  millisUntilFinished;


                    if (timeLeft <= 0) {
                        stopTimer();
                        MainActivity.getInstance().EndTurn(0);
                    }

                }



            @Override
            public void onFinish() {
                MainActivity.getInstance().EndTurn(0);
            }
        };
    }

    public void stopTimer() {
        countDownTimer.cancel();
        timeOffset = 0;
    }

    public void resetTimer() {
        timeLeft = timeAvailablePerQuestion;
        countDownTimer.start();
    }





}
