package com.work.terry.snakeonastring;

import android.content.Context;
import android.opengl.GLES30;
import android.opengl.GLSurfaceView;
import android.util.Log;
import android.view.MotionEvent;

import javax.microedition.khronos.opengles.GL10;
import javax.microedition.khronos.egl.EGLConfig;

import com.work.terry.snakeonastring.Util.TexDrawer;
import com.work.terry.snakeonastring.Util.ColorManager;
import com.work.terry.snakeonastring.Util.Constant;
import com.work.terry.snakeonastring.Util.ImgManager;
import com.work.terry.snakeonastring.Util.MatrixState;
import com.work.terry.snakeonastring.Util.TexManager;
import com.work.terry.snakeonastring.auto.*;

/**
 * Created by Terry on 2017/12/23.
 */

public class GamePlayView extends GLSurfaceView {

    private SceneRenderer sceneRenderer;

    Snake snake ;

    public GamePlayView(Context context){
        super(context);
        this.setEGLContextClientVersion(3);

        sceneRenderer = new SceneRenderer();
        setRenderer(sceneRenderer);
        setRenderMode(GLSurfaceView.RENDERMODE_CONTINUOUSLY);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event){
        if(snake == null)return true;
        int x = (int)event.getX();
        int y = (int)event.getY();
        int[]  xy = ScreenScaleUtil.touchFromTargetToOrigin(x,y, Constant.ssr);
        x = xy[0];
        y = xy[1];
        Log.d("touchMotion","x="+x+" y="+y);

        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                snake.whenMotionDown(x,y);
                break;
            case MotionEvent.ACTION_MOVE:
                snake.whenMotionDown(x,y);
                break;
            case MotionEvent.ACTION_UP:
                break;
        }

        return true;
    }

    private  class  SceneRenderer implements GLSurfaceView.Renderer {

        TexDrawer texDrawer;

        @Override
        public void onDrawFrame(GL10 gl){
            GLES30.glClear(GLES30.GL_DEPTH_BUFFER_BIT|GLES30.GL_COLOR_BUFFER_BIT);

            //snake.setColor(Constant.COLOR_DEFAULT);
            texDrawer.drawSelf(TexManager.getTex(Constant.backgroundImg), ColorManager.getColor(Constant.COLOR_DEFAULT),Constant.SCREEN_WIDTH/2,Constant.SCREEN_HEIGHT/2,Constant.SCREEN_WIDTH,Constant.SCREEN_HEIGHT,0);
            snake.drawSelf(texDrawer);
        }
        @Override
        public void onSurfaceChanged(GL10 gl, int width,int height){
            GLES30.glViewport(Constant.ssr.lucX,Constant.ssr.lucY,Constant.ssr.skWidth,Constant.ssr.skHeight);
            MatrixState.setProjectOrtho(
                    -Constant.ssr.vpRatio,
                    Constant.ssr.vpRatio,
                    -1,1,
                    1,10
            );

            MatrixState.setCamera(0,0,3,0f,0f,0f,0f,1.0f,0f);
            MatrixState.setInitStack();
        }
        @Override
        public void onSurfaceCreated(GL10 gl, EGLConfig config){
            GLES30.glClearColor(0,1,1,1.0f);
            texDrawer = new TexDrawer(GamePlayView.this);
            GLES30.glDisable(GLES30.GL_DEPTH_TEST);

            TexManager.addTexArray(ImgManager.picName);
            TexManager.loadTextures(GamePlayView.this.getResources());
            GLES30.glDisable(GLES30.GL_CULL_FACE);

            snake = new Snake();
            snake.moving();
        }
    }

    @Override
    public void onResume(){
        super.onResume();
        if(snake!=null)snake.onResume();
    }

    @Override
    public void onPause(){
        super.onPause();
        if(snake!=null)snake.onPause();
    }
}
