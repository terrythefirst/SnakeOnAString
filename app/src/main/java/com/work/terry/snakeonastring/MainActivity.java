package com.work.terry.snakeonastring;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

import com.work.terry.snakeonastring.Util.Constant;
import com.work.terry.snakeonastring.auto.ScreenScaleUtil;

public class MainActivity extends Activity {
    private GamePlayView gamePlayView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        WindowManager manager = this.getWindowManager();
        DisplayMetrics outMetrics = new DisplayMetrics();
        manager.getDefaultDisplay().getMetrics(outMetrics);
        Constant.SCREEN_WIDTH = outMetrics.widthPixels;
        Constant.SCREEN_HEIGHT = outMetrics.heightPixels;

        Constant.ssr = ScreenScaleUtil.calScale(Constant.SCREEN_WIDTH,Constant.SCREEN_HEIGHT);

        Log.d("ssr",Constant.ssr.toString());

        gamePlayView = new GamePlayView(this);
        setContentView(gamePlayView);
        gamePlayView.requestFocus();
        gamePlayView.setFocusableInTouchMode(true);
    }
    @Override
    protected void onResume(){
        super.onResume();
        gamePlayView.onResume();
    }
    @Override
    protected void onPause(){
        super.onPause();
        gamePlayView.onPause();
    }
}
