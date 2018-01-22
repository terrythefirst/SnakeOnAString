package com.work.terry.snakeonastring;

import android.util.Log;

import com.work.terry.snakeonastring.Util.TexDrawer;
import com.work.terry.snakeonastring.Util.ColorManager;
import com.work.terry.snakeonastring.Util.Constant;
import com.work.terry.snakeonastring.Util.TexManager;

import static com.work.terry.snakeonastring.Util.VectorUtil.*;
import static com.work.terry.snakeonastring.Util.Constant.*;
/**
 * Created by Terry on 2017/12/27.
 */

public class SnakeHead extends GameElements{

    private boolean flag = true;

//    public float x = Constant.SCREEN_WIDTH/2;
//    public float y = Constant.SCREEN_HEIGHT/2;
    public float HeadVX = 0;
    public float HeadVY = 1;
    //public float HeadDownHeight = Constant.SnakeDownHeight;
    //public float HeadJumpHeight;

    private float ratio = Constant.SnakeHeadRatio;

    private int speed = 5;
    private int SpeedFactor = 10;

    Target target = null;
    Thread movingThread;

    public SnakeHead(){
        x = Constant.SCREEN_WIDTH/2;
        y = Constant.SCREEN_HEIGHT/2;
        HeadVX = 0;
        HeadVY = 1;
    }
    public void moving(){
        target = new Target(x,y,0,0);
        movingThread =  new MovingThread();
        movingThread.start();
    }
    public SnakeHead(int x,int y,int vx,int vy){
        this.x = x;
        this.y = y;
        HeadVX = vx;
        HeadVY = vy;
    }
    public float[] getV2d(){
        return new float[]{HeadVX,HeadVY};
    }
    private class Target{
        public float TargetHeadX = 1280;
        public float TargetHeadY = 720;
        public float TargetHeadVX = 0;
        public float TargetHeadVY = 1;

        public Target(float touchX,float touchY,float HeadX,float HeadY){
            setTarget(touchX,touchY,HeadX,HeadY);
        }
        public void setTarget(float touchX,float touchY,float HeadX,float HeadY){
            TargetHeadX = touchX;
            TargetHeadY = touchY;
            float[] normalTXY = normalize2D(touchX - HeadX,touchY - HeadY);
            TargetHeadVX = normalTXY[0];
            TargetHeadVY = normalTXY[1];
            Log.d("Target","target x="+TargetHeadX+" y="+TargetHeadY+"\n vx="+TargetHeadVX+" vy="+TargetHeadVY);
        }
    }

    public void drawSelf(TexDrawer painter, float[] color){
        Log.d("SnakeHead","headx = "+x+" headY = "+y+"\n--HeadVX="+HeadVX+" HeadVY="+HeadVY);

//        if(HeadX<0)HeadX = 0;
//        else if(HeadX>Constant.bodyWidth)HeadX = Constant.bodyWidth;
//        if(HeadY<0)HeadY = 0;
//        else if(HeadY>Constant.bodyHeight)HeadY = Constant.bodyHeight;
        painter.drawColorSelf(
                TexManager.getTex(snakeHeadHeadImg),
                color,
                x+headWidth/2,
                y+headHeight/2-jumpHeight,
                headTopWidth,
                headTopHeight,
                0
        );
        painter.drawDownShadow(
                TexManager.getTex(snakeHeadEyesBallImg),
                color,
                x+headWidth/2,
                y+headHeight/2-jumpHeight,
                headEyesDiameter,
                headEyesDiameter,
                calRotateAngleDegrees(HeadVX,HeadVY),
                0,
                Constant.SnakeEyesDownLittleHeight,
                Constant.SnakeEyesDownLittleColorFactor
        );
        painter.drawSelf(
                TexManager.getTex(snakeHeadEyesImg),
                ColorManager.getColor(Constant.C0LOR_WHITE),
                x+headWidth/2,
                y+headHeight/2-jumpHeight,
                headEyesDiameter,
                headEyesDiameter,
                calRotateAngleDegrees(HeadVX,HeadVY)
        );

        //painter.drawSelf(TexManager.getTex(axisImg),HeadX+headWidth/2,HeadY+headHeight/2,headWidth,headHeight,calRotateAngleDegrees(HeadVX,HeadVY));
    }
    @Override
    public void drawHeightShadow(TexDrawer painter,float[] color){
        painter.drawDownShadow(
                TexManager.getTex(snakeHeadHeadImg),
                color,
                x+headWidth/2,
                y+headHeight/2,
                headWidth,
                headHeight,
                0,
                0,
                Constant.SnakeDownHeight,
                Constant.SnakeHeightColorFactor
        );
        painter.drawDownShadow(
                TexManager.getTex(snakeBodyHeightImg),
                color,
                x+headWidth/2,
                y+headHeight/2-Constant.SnakeDownHeight/2-jumpHeight/2,
                headWidth*ratio,
                Constant.SnakeDownHeight+jumpHeight,
                0,
                0,
                Constant.SnakeDownHeight,
                Constant.SnakeHeightColorFactor
        );
    }
    @Override
    public void drawDownLittleShadow(TexDrawer painter,float[] color){
        painter.drawDownShadow(
                TexManager.getTex(snakeHeadHeadImg),
                color,
                x+headWidth/2,
                y+headHeight/2-jumpHeight,
                headWidth,
                headHeight,
                0,
                0,
                Constant.SnakeDownLittleHeight,
                Constant.SnakeDownLittleColorFactor
        );
    }
    @Override
    public void drawFloorShadow(TexDrawer painter,float[] color){
        painter.drawFloorShadow(
                TexManager.getTex(snakeHeadHeadImg),
                color,
                x+headWidth/2,
                y+headHeight/2+Constant.SnakeDownHeight,
                headWidth,
                headHeight,
                0,
                (Constant.SnakeDownHeight+jumpHeight)*Constant.FloorShadowFactorX,
                (Constant.SnakeDownHeight+jumpHeight)*Constant.FloorShadowFactorY,
                Constant.SnakeFloorColorFactor
        );
//        painter.drawFloorShadow(
//                TexManager.getTex(snakeBodyHeightImg),
//                color,
//                x+headWidth/2,
//                y+headHeight/2+Constant.SnakeDownHeight,
//                headWidth,
//                headHeight,
//                0,
//                Constant.SnakeDownHeight,
//                Constant.SnakeFloorColorFactor
//        );
    }
    public void whenMotionDown(int touchX,int touchY){
        if(touchX!=x||touchY!=y){
            target.setTarget(touchX,touchY,x,y);
        }
    }

     private class MovingThread extends Thread {
        public void run () {
            while (flag) {
                if (target != null && (x != target.TargetHeadX || y != target.TargetHeadY)) {
                    float dx = target.TargetHeadX - x;
                    float dy = target.TargetHeadY - y;
                    float[] normalXY = normalize2D(dx, dy);
                    x += normalXY[0] * speedFactor(dx);
                    y += normalXY[1] * speedFactor(dy);

                    float dvx = target.TargetHeadVX - HeadVX;
                    float dvy = target.TargetHeadVY - HeadVY;
                    //float dvs = calDistance(dvx, dvy);
                    float[] normalvXY = normalize2D(dvx, dvy);
                    float[] nornalvPresentXY = normalize2D(HeadVX,HeadVY);
                    HeadVX = nornalvPresentXY[0];
                    HeadVY = nornalvPresentXY[1];

                    float dotMul = dotMul2D(dvx, dvy, HeadVX, HeadVY);
                    Log.d("dotMul", dotMul + "");
                    if (!isReverse2D(dvx, dvy, HeadVX, HeadVY) && Math.abs(dotMul) > 0.5f) {
                        HeadVX += normalvXY[0];
                        HeadVY += normalvXY[1];
                    } else {
                        HeadVX = target.TargetHeadVX;
                        HeadVY = target.TargetHeadVY;
                    }
                }
                try {
                    sleep(10);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
    private int speedFactor(float x){
        if(Math.abs(x)>SpeedFactor)return speed;
        else return 0;
    }
    public void onResume(){
        flag = true;
        movingThread = new MovingThread();
    }
    public void onPause(){
        flag = false;
    }
}
