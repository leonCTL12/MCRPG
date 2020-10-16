package hku.hkucs.mcrpg;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;

public class SoundEffectManager {
    private static SoundPool soundPool;
    private static int correctSound;
    private static int wrongSound;

    public  SoundEffectManager(Context context) {
            soundPool = new SoundPool(2, AudioManager.STREAM_MUSIC, 0);
            correctSound = soundPool.load(context, R.raw.correctsoundfx, 1);
            wrongSound = soundPool.load(context, R.raw.wrongsoundfx, 1);

    }

    public void PlayCorrectSoundFX() {
        System.out.println("play Correct sound fx");
       soundPool.play(correctSound,1f, 1f, 1, 0, 1.0f );



    }

    public void PlayWrongSoundFX() {
        System.out.println("Play Wrong sound fx");
        soundPool.play(wrongSound,0.2f, 0.2f, 1, 0, 1.0f );
    }

}
