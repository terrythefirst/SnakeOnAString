package com.work.terry.snakeonastring;

import com.work.terry.snakeonastring.Util.TexDrawer;

/**
 * Created by Terry on 2018/1/2.
 */

public abstract class GameElements{
    float x;
    float y;
    float jumpHeight;

    public abstract void drawSelf(TexDrawer painter, float[] color);
    public abstract void drawHeightShadow(TexDrawer painter,float[] color);
    public abstract void drawDownLittleShadow(TexDrawer painter,float[] color);
    public abstract void drawFloorShadow(TexDrawer painter,float[] color);

    public abstract void onResume();
    public abstract void onPause();

    public static float JumpMath(float jumpSpan,float JumpMathFactor,float x){
        float b = jumpSpan;
        float a = (float)(-jumpSpan/Math.pow(JumpMathFactor,2));

        return b+a*(float)Math.pow(x-JumpMathFactor,2);
    }
}
