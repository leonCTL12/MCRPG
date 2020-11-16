package hku.hkucs.mcrpg;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;

public class SoundEffectManager {
    private static SoundPool soundPool;
    private static int correctSound;
    private static int wrongSound;
    private static int buttonClickedSound;
    private static int playerGotSlashedSound;
    private static int skillClickedSound;
    private static int swordSlashSound;
    private static int timeTickingSound;
    private static int blurringAnswerSound;
    private static int disablingAnswerSound;
    private static int healingSound;
    private static int lockingSkillSound;
    private static int magicCupSound;
    private static int scramblingAnswerSound;
    private static int unlockingSkillSound;
    private static int timeSlowingDownSound;
    private static int timeRewindSound;
    private static int hardQuestionSound;
    private static int monsterDebut;
    private static int monsterDeath;


    public  SoundEffectManager(Context context) {
        soundPool = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);
        correctSound = soundPool.load(context, R.raw.correctsoundfx, 1);
        wrongSound = soundPool.load(context, R.raw.wrongsoundfx, 1);
        buttonClickedSound = soundPool.load(context, R.raw.buttonclicked, 1);
        playerGotSlashedSound =soundPool.load(context, R.raw.playergotslashed, 1);
        skillClickedSound =soundPool.load(context, R.raw.skillclicked, 1);
        swordSlashSound =soundPool.load(context, R.raw.swordslash, 1);
        timeTickingSound = soundPool.load(context, R.raw.timeticking, 1);
        blurringAnswerSound =soundPool.load(context, R.raw.bluranswer, 1);
        disablingAnswerSound =soundPool.load(context, R.raw.disableans, 1);
        healingSound = soundPool.load(context, R.raw.healsound, 1);
        lockingSkillSound = soundPool.load(context, R.raw.lock, 1);
        magicCupSound = soundPool.load(context, R.raw.magiccup, 1);
        scramblingAnswerSound = soundPool.load(context, R.raw.scrambled, 1);
        unlockingSkillSound = soundPool.load(context, R.raw.unlock, 1);
        timeSlowingDownSound = soundPool.load(context, R.raw.tineslowdown, 1);
        timeRewindSound = soundPool.load(context, R.raw.timerewind, 1);
        hardQuestionSound = soundPool.load(context, R.raw.hardquestionsound, 1);
        monsterDebut = soundPool.load(context, R.raw.monsterdebut, 1);
        monsterDeath = soundPool.load(context, R.raw.monsterdeath, 1);


    }

    public void PlayMonsterDeathSoundFX() {
        soundPool.play(monsterDeath,1f, 1f, 1, 0, 1.0f );
    }


    public void PlayCorrectSoundFX() {
        System.out.println("play Correct sound fx");
        soundPool.play(correctSound,1f, 1f, 1, 0, 1.0f );
    }

    public void  PlayMonsterDebutSoundFX() {
        soundPool.play(monsterDebut,1f, 1f, 1, 0, 1.0f );

    }

    public void PlayWrongSoundFX() {
        System.out.println("Play Wrong sound fx");
        soundPool.play(wrongSound,0.2f, 0.2f, 1, 0, 1.0f );
    }

    public void PlayButtonClickedFX(){
        System.out.println("Play button clicked sound fx");
        soundPool.play(buttonClickedSound,1f, 1f, 1, 0, 1.0f );
    }
    public void PlaySwordSlashFX(){
        System.out.println("Play sword slash sound fx");
        soundPool.play(swordSlashSound,1f, 1f, 1, 0, 1.0f );
    }
    public void PlayBlurringAnsFX(){
        System.out.println("Play blurring answer sound fx");
        soundPool.play(blurringAnswerSound,1f, 1f, 1, 0, 1.0f );
    }
    public void PlayDisablingAnsFX(){
        System.out.println("Play disabling ans sound fx");
        soundPool.play(disablingAnswerSound,1f, 1f, 1, 0, 1.0f );
    }
    public void PlayHealingFX(){
        System.out.println("Play healing sound fx");
        soundPool.play(healingSound,1f, 1f, 1, 0, 1.0f );
    }
    public void PlayLockingFX(){
        System.out.println("Play locking sound fx");
        soundPool.play(lockingSkillSound,1f, 1f, 1, 0, 1.0f );
    }
    public void PlayMagicCupFX(){
        System.out.println("Play magic cup sound fx");
        soundPool.play(magicCupSound,1f, 1f, 1, 0, 1.0f );
    }
    public void PlayPlayerGotSlashedFX(){
        System.out.println("Play player got slashed sound fx");
        soundPool.play(playerGotSlashedSound,1f, 1f, 1, 0, 1.0f );
    }
    public void PlayScrambledFX(){
        System.out.println("Play scrambling ans fx");
        soundPool.play(scramblingAnswerSound,1f, 1f, 1, 0, 1.0f );
    }
    public void PlaySkilledClickedFX(){
        System.out.println("Play Skilled Clicked sound fx");
        soundPool.play(skillClickedSound,1f, 1f, 1, 0, 1.0f );
    }
    public void PlayTimeRewindFX(){
        System.out.println("Play Time rewinding sound fx");
        soundPool.play(timeRewindSound,3f, 3f, 1, 0, 1.0f );
    }
    public void PlayTimeTickingFX(){
        System.out.println("Play time ticking sound fx");
        soundPool.play(timeTickingSound,1f, 1f, 1, 0, 1.0f );
    }
    public void PlayTimeSlowDownFX(){
        System.out.println("Play time slow down sound fx");
        soundPool.play(timeSlowingDownSound,1f, 1f, 1, 0, 1.0f );
    }
    public void PlayUnlockFX(){
        System.out.println("Play unlock sound fx");
        soundPool.play(unlockingSkillSound,1f, 1f, 1, 0, 1.0f );
    }
    public void PlayHardQFX(){
        System.out.println("Play hard q sound fx");
        soundPool.play(hardQuestionSound,1f, 1f, 1, 0, 1.0f );
    }

}
