import android.os.CountDownTimer;

public class Player {

    private float health = 100f;
    private  float maximumAttackDamage = 50f;
    private CountDownTimer countDownTimer;
    private long timeLeft = 30000; //That is 30 sec
    private long timeAvailablePerQuestion = 30000; //That is 30 sec
    private boolean heal;
    private int removeOptions_CoolDown = 8;
    private int delayEnemyAttack_CoolDown = 10;
    private int heal_CoolDown = 4;
    private int increaseAvailableTimeCoolDown = 6;


    public void underAttack(float damage) {
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
    }

    public void playerStartTurn() {
        resetTimer();
    }

    public void playerEndTurn() {
        removeOptions_CoolDown--;
        delayEnemyAttack_CoolDown--;
        heal_CoolDown--;
        increaseAvailableTimeCoolDown--;

        stopTimer();
        heal = false; //To reset the effect if player use heal this turn;
        timeAvailablePerQuestion = 30000; //To reset the effect if player use lengthen answer time this turn;
    }

    public void increaseAvailableTime() {
        timeLeft+=30000; //we can directly increase the time left instead of restarting the timer
        timeAvailablePerQuestion = 60000;  //we set it just to calculate the damage player dealt proportionally, this will be reset at the end of the turn
    }

    public void startTimer() {
        countDownTimer = new CountDownTimer(timeLeft,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeft = millisUntilFinished;
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
        //TODO: update the timer text on the UI
    }





}
