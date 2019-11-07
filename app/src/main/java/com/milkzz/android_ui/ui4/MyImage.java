package com.milkzz.android_ui.ui4;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Created by zuoqi on 2019/11/6
 */
public class MyImage extends android.support.v7.widget.AppCompatImageView {
    Paint paint;
    Path p;
    Path p2;
    float v = 0;
    PathMeasure pathMeasure;

    public MyImage(Context context) {
        this(context, null);
    }

    public MyImage(Context context, AttributeSet attrs) {
        super(context, attrs);
        setLayerType(LAYER_TYPE_SOFTWARE, null);

        paint = new Paint();
        paint.setStrokeWidth(4);
        paint.setStyle(Paint.Style.STROKE);
        p = new Path();
        p.addCircle(-50, -50, 50, Path.Direction.CW);
        p2 = new Path();
        pathMeasure = new PathMeasure(p, true);

        ValueAnimator animator = ValueAnimator.ofFloat(0, 1);
        animator.setRepeatCount(1);
        animator.setDuration(2000);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                v = (float) animation.getAnimatedValue();
                invalidate();
            }
        });
        animator.start();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawColor(Color.WHITE);
        canvas.translate(100, 100);

        float stop = pathMeasure.getLength() * v;

        p2.reset();
        pathMeasure.getSegment(0, stop, p2, true);
        canvas.drawPath(p2, paint);
    }
}
