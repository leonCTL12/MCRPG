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
    private boolean heal;
    private int skill1CoolDown = 8; //eliminate wrong ans

    private int skill2CoolDown = 10; //delay monster attack

    private int skill3CoolDown = 6; //Increase thinking time

    private int skill4CoolDown = 4; //heal


    public int[] skillsCoolDown = {skill1CoolDown, skill2CoolDown, skill3CoolDown, skill4CoolDown};

    public void underAttack(int damage) {
            health -= damage;
            if (health < 0) {
                //TODO: do sth to notify the player
                System.out.println("You are dead");
            }
    }

    //Attack will only be called when the player answer the correct answer
    public void Attack() {
        float damageDealt = maximumAttackDamage * (timeAvailablePerQuestion - timeLeft)/timeAvailablePerQuestion;
        //TODO: call monster's receive damage method

        if (heal) {
            heal(damageDealt);
        }
    }

    public void heal(float healAmount) {
        health+=healAmount;
        heal = false;
        skillsCoolDown[3] = skill4CoolDown;
    }

    public void playerStartTurn() {

        resetTimer();
    }

    public void playerEndTurn() {

        for (int i = 0; i < 4; i++) {
            if (skillsCoolDown[i] > 0) {
                skillsCoolDown[i]--;
                System.out.println("cool down updated");
            }
        }
//Add back timer later
       stopTimer();
        heal = false; //To reset the effect if player use heal this turn;
        additionTime = 0;
        timeAvailablePerQuestion = 30000; //To reset the effect if player use lengthen answer time this turn;
    }

    public void increaseAvailableTime() {
        additionTime+=30000; //we can directly increase the additionTime instead of restarting the timer
        timeAvailablePerQuestion = 60000;  //we set it just to calculate the damage player dealt proportionally, this will be reset at the end of the turn
        skillsCoolDown[2] = skill3CoolDown;
        System.out.println("updated skill3 cd");
    }

    public void startTimer() {
        countDownTimer = new CountDownTimer(timeLeft,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeft = additionTime +  millisUntilFinished;
                //TODO: update the text in the timer with this timer UI;
            }

            @Override
            public void onFinish() {
                System.out.println("Time's up!");
                //TODO: randomly choose a answer for the player when he is run out of time
            }
        }.start();
    }

    public void stopTimer() {
        countDownTimer.cancel();
        Attack();
    }

    public void resetTimer() {
        timeLeft = timeAvailablePerQuestion;
       startTimer();

    }





}
