package com.work.terry.snakeonastring;

import com.work.terry.snakeonastring.Util.TexDrawer;

/**
 * Created by Terry on 2017/12/30.
 */

public class Button extends GameElements{
    public int Color = 1;

    private float circleDiameter;
    private float rectLength;
    private float rotateAngle;

    public Button(int x,int y,float circleDiameter,float totalLength,int Color,float rotateAngle){
        this.x = x;
        this.y = y;
        this.circleDiameter = circleDiameter;
        rectLength = totalLength-circleDiameter;


    }

    public void drawSelf(TexDrawer painter, float[] color){
//        painter.drawSelf(
//                TexManager.getTex(Constant.buttonImgCircle),
//                x,
//                y,
//
//
//        );
    }
    public void drawHeightShadow(TexDrawer painter,float[] color){

    }
    public void drawDownLittleShadow(TexDrawer painter,float[] color){

    }
    public void drawFloorShadow(TexDrawer painter,float[] color){

    }

    public void onResume(){

    }
    public void onPause(){

    }

}
