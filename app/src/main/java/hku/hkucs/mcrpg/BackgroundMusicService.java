package hku.hkucs.mcrpg;
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import androidx.annotation.Nullable;

public class BackgroundMusicService extends Service {
    MediaPlayer mediaPlayer;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mediaPlayer = MediaPlayer.create(this, R.raw.bgm_1);
        System.out.println("Playing");
        mediaPlayer.setLooping(true);
        mediaPlayer.setVolume(100,100);
        mediaPlayer.start();
    }
}
