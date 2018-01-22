package com.work.terry.snakeonastring;


import com.work.terry.snakeonastring.Util.TexDrawer;
import com.work.terry.snakeonastring.Util.ColorManager;
import com.work.terry.snakeonastring.Util.Constant;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Terry on 2017/12/28.
 */

public class Snake {
    private SnakeHead snakeHead;
    private List<GameElements> snakeBodies = null;//包括头！！！
    private AnimateThread animateThread = null;
    private static float[] Color = ColorManager.getColor(Constant.C0LOR_WHITE);
    private boolean isDead = false;
    private List<GameElements> drawSequence = null;

    public Snake(){
        snakeHead = new SnakeHead();
        snakeBodies = new ArrayList<>();
        snakeBodies.add(snakeHead);
        for(int i = 1;i<=Constant.SnakeBodyDefaultLength;i++){
            addBody();
        }
        drawSequence = new ArrayList<GameElements>();
        for(int i = 0;i<snakeBodies.size();i++){
            GameElements tempt = snakeBodies.get(i);
            drawSequence.add(tempt);
        }
        //calDrawSequence();

        animateThread = new AnimateThread();
        animateThread.start();
    }
    private class AnimateThread extends Thread{
        @Override
        public void run(){
            int time = 0;
            int index = -1;
            int timeLimit = (int) (2*Constant.JumpMathFactor);
            while (!isDead){
                if(index==-1)
                snakeHead.jumpHeight = GameElements.JumpMath(Constant.SnakeDownHeight + 10 ,Constant.JumpMathFactor,time);
                else {
                    snakeBodies.get(index).jumpHeight =  GameElements.JumpMath(Constant.SnakeDownHeight + 10 ,Constant.JumpMathFactor,time);
                }

                if(time<timeLimit){
                    time++;
                }else {
                    time = 0;
                    index++;
                    if(index>=snakeBodies.size())index = -1;
                }

                try {
                    sleep(50);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }

        }
    }

    public void addBody(){
        if(snakeBodies.size()==1){
            snakeBodies.add(new SnakeBody(snakeHead));
        }else{
            snakeBodies.add(new SnakeBody((SnakeBody) snakeBodies.get(snakeBodies.size()-1)));
        }
    }
    public void drawSelf(TexDrawer painter){
        snakeBodies.stream()
                .sorted(Comparator.comparing(x->x.y))
                .forEach(
                        x->{
                            x.drawFloorShadow(painter,Color);
                        }
                );
        snakeBodies.stream()
                .sorted(Comparator.comparing(x->x.y))
                .forEach(
                        x->{
                            x.drawHeightShadow(painter,Color);
                            x.drawDownLittleShadow(painter,Color);
                            x.drawSelf(painter,Color);
                        }
                );
                //.collect(Collectors.toList());
        //calDrawSequence();
//        drawSequence.stream()
//                .forEach(
//                        x->{
//                            x.drawHeightShadow(painter,Color);
//                            x.drawDownLittleShadow(painter,Color);
//                            x.drawSelf(painter,Color);
//                        }
//                );
//        for(GameElements gameElements:drawSequence){
//            Log.d("sequence",gameElements.toString());
//            gameElements.drawHeightShadow(painter,Color);
//            gameElements.drawDownLittleShadow(painter,Color);
//            gameElements.drawSelf(painter,Color);
//        }
    }
//    public void drawDownShadow(TexDrawer painter){
//        for(int i=snakeBodies.size()-1;i>=0;i--){
//            snakeBodies.get(i).drawHeightShadow(painter,Color);
//        }
//        snakeHead.drawHeightShadow(painter,Color);
//
//        for(int i=snakeBodies.size()-1;i>=0;i--){
//            snakeBodies.get(i).drawDownLittleShadow(painter,Color);
//        }
//        snakeHead.drawDownLittleShadow(painter,Color);
//    }
    public void calDrawSequence(){
        drawSequence = drawSequence.stream()
                .sorted(Comparator.comparing(x->x.y))
                .collect(Collectors.toList());
    }
    public void drawFloorShadow(TexDrawer painter){

    }
    public void whenMotionDown(int x,int y){
        snakeHead.whenMotionDown(x,y);
    }
    public void moving(){
        snakeHead.moving();
    }

    public void setColor(int index){
        Color = ColorManager.getColor(index);
    }


    public void onResume(){
        drawSequence.stream()
                .forEach(
                        x-> x.onResume()
                );
    }
    public void onPause(){
        drawSequence.stream()
                .forEach(
                        x-> x.onPause()
                );
    }

}
