package hku.hkucs.mcrpg;

import android.graphics.Canvas;
import android.graphics.drawable.ClipDrawable;
import android.widget.ImageView;

public class HealthBar {

    private ImageView imageView;
    private ClipDrawable clipDrawable;

    public HealthBar(ImageView inputImageView) {
        imageView = inputImageView;
        clipDrawable = (ClipDrawable) imageView.getDrawable();
    }

    // Usage: input a health percentage between 0 to 1
    public void setHealth(float input) {
        int value = (int) (input * 10000);
        //System.out.println("Monster debug: " + String.valueOf(input));
        clipDrawable.setLevel(value);
    }
}
