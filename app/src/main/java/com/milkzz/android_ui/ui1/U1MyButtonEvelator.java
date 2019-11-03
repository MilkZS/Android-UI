package com.milkzz.android_ui.ui1;

import android.animation.TypeEvaluator;
import android.graphics.Point;

/**
 * Created by zuoqi on 2019/11/3
 */
public class U1MyButtonEvelator implements TypeEvaluator<Point> {

    private Point p = new Point();

    @Override
    public Point evaluate(float fraction, Point startValue, Point endValue) {

        p.x = (int) (startValue.x + fraction * (endValue.x - startValue.x));
        p.y = (int) (startValue.y + fraction * (endValue.y - startValue.y));
        return p;
    }
}
