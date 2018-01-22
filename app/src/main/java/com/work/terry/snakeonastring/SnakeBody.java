package com.work.terry.snakeonastring;

import android.util.Log;

import com.work.terry.snakeonastring.Util.TexDrawer;
import com.work.terry.snakeonastring.Util.Constant;
import com.work.terry.snakeonastring.Util.TexManager;

import static com.work.terry.snakeonastring.Util.VectorUtil.*;
import static com.work.terry.snakeonastring.Util.Constant.*;
/**
 * Created by Terry on 2017/12/28.
 */

public class SnakeBody extends GameElements{

    private boolean flag=true;
    //public float BodyDownHeight = Constant.SnakeDownHeight;
    //public float jumpHeight = 0;
    private Front front = null;
    private MovingThread movingThread = null;

    private class MovingThread extends Thread{
        @Override
        public void run(){
            while(flag){
                float[] frontXY = front.getXY();
                float dx = frontXY[0] - x;
                float dy = frontXY[1] - y;
                float[] frontV = front.getV2D();
                frontV = normalize2D(frontV[0],frontV[1]);
                float[] targetMoveV = plusV2D(new float[]{dx,dy},frontV);
                targetMoveV = normalize2D(targetMoveV[0],targetMoveV[1]);
                x = frontXY[0]-targetMoveV[0]*front.getCenterDistance();
                y = frontXY[1]-targetMoveV[1]*front.getCenterDistance();

                try {
                    sleep(10);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
    }
    private class Front{
        SnakeHead head= null;
        SnakeBody frontBody = null;
        public float width = 0;
        public float height = 0;
        private float CenterDistanceX = 0;
        private float CenterDistanceY = 0;
        private float CenterDistance = 0;

        public Front(SnakeBody front){
            this.frontBody = front;
            width = bodyWidth;
            height = bodyHeight;
            calCenterDistance();
        }
        public Front(SnakeHead front){
            this.head = front;
            width = headWidth;
            height = headHeight;
            calCenterDistance();
        }
        public float[] getV2D(){
            if(head!=null)return head.getV2d();
            else {
                float[] frontXY = frontBody.front.getXY();
                float[] xy = this.getXY();
                return new float[]{frontXY[0]-xy[0],frontXY[1]-xy[1]};
            }
        }
        public float[] getXY(){
            if(head!=null) return new float[]{head.x,head.y};
            else return new float[]{frontBody.x,frontBody.y};
        }
        public float getWidth(){
            return width;
        }
        public float getHeight(){
            return height;
        }

        private void calCenterDistance(){
            CenterDistanceX = (getWidth()+ Constant.bodyWidth)/2;
            CenterDistanceY = (getHeight()+Constant.bodyHeight)/2;
            CenterDistance = calDistance(CenterDistanceX,CenterDistanceY);
            if(head!=null)CenterDistance-=Constant.DistanceOffset*5/4;
            else CenterDistance-=Constant.DistanceOffset;
        }
        public float getCenterDistance(){
            return CenterDistance;
        }
    }

    public SnakeBody(SnakeBody frontNode){
        this.front = new Front(frontNode);
        initSelf();
    }
    public SnakeBody(SnakeHead frontNode){
        this.front = new Front(frontNode);
        initSelf();
    }
    public void initSelf(){
        float[] frontV = this.front.getV2D();
        frontV = normalize2D(frontV[0],frontV[1]);
        float[] frontXY = front.getXY();
        Log.d("FrontV","FrontVx = "+frontV[0]+" Front = "+frontV[1]);
        x = frontXY[0]-frontV[0]*front.getCenterDistance();
        y = frontXY[1]-frontV[1]*front.getCenterDistance();

        movingThread = new MovingThread();
        movingThread.start();
    }
    public void drawSelf(TexDrawer painter, float[] color){

        Log.d("SnakeBody","bodyX= "+x+" bodyY = "+y);

//        if(BodyX<0)BodyX = 0;
//        else if(BodyX>2560)BodyX = 2560;
//        if(BodyY<0)BodyY = 0;
//        else if(BodyY>1440)BodyY = 1440;

        painter.drawColorSelf(
                TexManager.getTex(snakeBodyImg),
                color,
                x+headWidth/2,
                y+headHeight/2-jumpHeight,
                bodyTopWidth,
                bodyTopHeight,
                0
        );

        //painter.drawSelf(TexManager.getTex(axisImg),BodyX+headWidth/2,BodyY+headHeight/2,bodyWidth,bodyHeight,0);
        //注意身体的xy 加上的是头部长宽的一半 让头部的中心和身体的中心 正确对应图片中心
    }
    @Override
    public void drawHeightShadow(TexDrawer painter,float[] color){
        painter.drawDownShadow(
                TexManager.getTex(snakeBodyImg),
                color,x+headWidth/2,
                y+headHeight/2,
                bodyWidth,
                bodyHeight,
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
                bodyWidth,
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
                TexManager.getTex(snakeBodyImg),
                color,
                x+headWidth/2,
                y+headHeight/2-jumpHeight,
                bodyWidth,
                bodyHeight,
                0,
                0,
                Constant.SnakeDownLittleHeight,
                Constant.SnakeDownLittleColorFactor
        );
    }
    @Override
    public void drawFloorShadow(TexDrawer painter,float[] color){
        painter.drawFloorShadow(
                TexManager.getTex(snakeBodyImg),
                color,
                x+headWidth/2,
                y+headHeight/2+Constant.SnakeDownHeight,
                bodyWidth,
                bodyHeight,
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
//                bodyWidth,
//                bodyHeight,
//                30,
//                Constant.SnakeDownHeight+jumpHeight,
//                Constant.SnakeFloorColorFactor
//        );
    }
    public void onResume(){
        flag = true;
        movingThread = new MovingThread();
    }
    public void onPause(){
        flag = false;
    }
}
