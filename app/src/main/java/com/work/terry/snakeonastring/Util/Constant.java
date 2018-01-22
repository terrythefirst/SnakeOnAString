package com.work.terry.snakeonastring.Util;

/**
 * Created by Terry on 2017/12/23.
 */
import com.work.terry.snakeonastring.auto.*;

public class Constant {
    public static int SCREEN_WIDTH ;
    public static int SCREEN_HEIGHT;
    public static ScreenScaleResult ssr;
    public static final float RATE = 10;//屏幕到现实世界的比例 10px：1m;

    public static final float FloorShadowFactorX = 0.3f;
    public static final float FloorShadowFactorY = 0.5f;

    public static int DistanceOffset = 40;
    public static final int SnakeBodyDefaultLength = 6;
    public static final int SnakeDownHeight = 30;
    public static final int SnakeDownLittleHeight = 7;
    public static final float SnakeDownLittleColorFactor = 0.87f;
    public static final int SnakeEyesDownLittleHeight = 5;
    public static final float SnakeEyesDownLittleColorFactor = 0.90f;
    public static final float SnakeHeightColorFactor = 0.80f;
    public static final float SnakeFloorColorFactor = 0.40f;

    public static final float JumpMathFactor = 4;

    public static String picDirectoryPrefix = "pic/";


    public static String snakeHeadHeadImg = "snake_head_head.png";
    public static String snakeHeadEyesImg = "snake_head_eyes.png";
    public static String snakeHeadEyesBallImg = "snake_head_eyeBalls.png";
    public static final float SnakeHeadRatio = (float) (800*1.0/1024);
    public static final int headEyesDiameter = 120;
    public static final int headTopWidth = 96;
    public static final int headWidth = 100;
    public static final int  headTopHeight = 96;
    public static final int  headHeight = 100;


    public static String snakeBodyImg = "snake_body.png";
    public static String snakeBodyHeightImg ="snake_body_height.png";
    public static final int bodyTopWidth = 66;
    public static final int bodyWidth = 70;
    public static final int  bodyTopHeight = 66;
    public static final int  bodyHeight = 70;

    public static String backgroundImg = "Strips_background.png";
    public static String buttonImgCircle = snakeBodyImg;
    public static String buttonImgRect = snakeBodyHeightImg;

    public static String axisImg = "axis_with_direction.png";
    public static int axisWidth = headWidth;
    public static int axisHeight = headHeight;

    public static final int COLOR_DEFAULT = 0;
    public static final int C0LOR_WHITE = 1;
    public static final int COLOR_ORANGE = 2;
    public static final int C0LOR_CYAN = 3;
}
