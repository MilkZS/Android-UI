package com.milkzz.android_ui.ui5;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by zuoqi on 2019/11/9
 */
public class MyZIMage extends android.support.v7.widget.AppCompatTextView {
    public MyZIMage(Context context) {
        this(context,null);
    }

    Paint p;
    Paint paint1;
    Path p1;
    Path p2;
    Path p3;
    PathMeasure pathMeasure;

    float forcatiion;

    boolean ifFirst = true;

    public MyZIMage(Context context, AttributeSet attrs) {
        super(context, attrs);

        p = new Paint();
        p.setStrokeWidth(4);
        p.setStyle(Paint.Style.STROKE);

        paint1 = new Paint();
        paint1.setStyle(Paint.Style.FILL);
        paint1.setColor(Color.BLUE);

        p1 = new Path();
        p1.addCircle(150,150,100,Path.Direction.CCW);
        p1.moveTo(100,150);
        p1.lineTo(150,200);
        p1.lineTo(200,125);
        p2 = new Path();

        p3 = new Path();
        p3.addCircle(150,150,100,Path.Direction.CCW);
        pathMeasure = new PathMeasure(p1,false);
        ValueAnimator animator = ValueAnimator.ofFloat(0,2);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                forcatiion = (float) animation.getAnimatedValue();
                invalidate();
            }
        });
        animator.setDuration(4000);
        animator.start();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawColor(Color.WHITE);


        if (forcatiion < 1){
            float st = forcatiion * pathMeasure.getLength();
            pathMeasure.getSegment(0,st,p2,true);
        }else {
            if (ifFirst){
                ifFirst = false;
                pathMeasure.nextContour();
            }
            float st = (forcatiion - 1) * pathMeasure.getLength();

            pathMeasure.getSegment(0,st,p2,true);
           
        }

        canvas.drawPath(p2,p);
    }
}
