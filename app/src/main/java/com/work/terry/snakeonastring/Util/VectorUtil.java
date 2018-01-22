package com.work.terry.snakeonastring.Util;

/**
 * Created by Terry on 2017/12/28.
 */

public class VectorUtil {
    public static boolean isReverse2D(float x2,float y2,float x1,float y1){
        return x2*y1-x1*y2 == 0&&((x1*x2<0)||(y1*y2<0));
    }
    public static float calDistance(double dx,double dy){
        return (float)Math.sqrt(Math.pow(dx,2)+Math.pow(dy,2));
    }
    public static float[] normalize2D(float x,float y){
        double ds =  Math.sqrt(Math.pow(x,2)+Math.pow(y,2));
        return new float[]{(float)(x/ds),(float)(y/ds)};
    }
    public static float dotMul2D(float x2, float y2, float x1, float y1){
        return x2*y1+x1*y2;
    }

    public static float calDistance(int x2,int y2,int x1,int y1){
        return (float)Math.sqrt(Math.pow(x1-x2,2)+Math.pow(y1-y2,2));
    }
    public static float calRotateAngleDegrees(float dx,float dy){
        float res = 0;
        if(dx >0){
            if (dy > 0) {
                res = (float)Math.toDegrees(Math.atan(dx/dy));
            }else if(dy < 0){
                res = 90 - (float)Math.toDegrees(Math.atan(dy/dx));
            }else if(dy == 0){
                res = 90;
            }
        }else if(dx <0){
            if (dy > 0) {
                res = 270 - (float)Math.toDegrees(Math.atan(dy/dx));
            }else if(dy < 0){
                res = 180 + (float)Math.toDegrees(Math.atan(dx/dy));
            }else if(dy == 0){
                res = 270;
            }
        }else if(dx == 0){
            if (dy > 0) {
                res = 0;
            }else if(dy < 0){
                res = 180;
            }else if(dy == 0){
                res = 0;
            }
        }
        return res;
    }
    public static float calRotateAngleRadius(float dx,float dy){
        return (float) Math.toRadians(calRotateAngleDegrees(dx,dy));
    }
    public static float[] plusV2D(float[] v1,float[] v2){
        return new float[]{v1[0]+v2[0],v1[1]+v2[1]};
    }
}
