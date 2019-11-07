package com.milkzz.android_ui.ui4;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.milkzz.android_ui.R;

/**
 * Created by zuoqi on 2019/11/7
 */
public class MyView1 extends View {

    private Paint p;
    private Path path;
    PathMeasure pm;
    private Bitmap bitmap;
    Path p2;

    float forcationl;

    float[] pos = new float[2];
    float[] tan = new float[2];

    Matrix matrix;

    public MyView1(Context context) {
        this(context, null);
    }

    public MyView1(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyView1(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        p = new Paint();
        p.setStyle(Paint.Style.STROKE);
        p.setColor(Color.BLACK);
        p.setStrokeWidth(4);

        matrix= new Matrix();


//        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.i1);
        path = new Path();
        p2 = new Path();
        path.addCircle(-50, -50, 100, Path.Direction.CW);
        pm = new PathMeasure();
        pm.setPath(path, true);
        ValueAnimator animator = ValueAnimator.ofFloat(0, 1);
        animator.setDuration(5000);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                forcationl = (float) animation.getAnimatedValue();
                invalidate();
            }
        });
        animator.start();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawColor(Color.WHITE);
        canvas.translate(300,300);

        float st = forcationl * pm.getLength();
        p2.reset();
        pm.getSegment(0,st,p2,true);

        if (bitmap == null) Log.e("EEE","null ");

        float degrees = (float) (Math.atan2(tan[1],tan[0]) * 180.0 / Math.PI);
        pm.getPosTan(st,pos,tan);
        matrix.postRotate(degrees,bitmap.getWidth()/2,bitmap.getHeight()/2);
        matrix.postTranslate(pos[0],pos[1]);
        canvas.drawBitmap(bitmap,matrix,p);
        canvas.drawPath(p2,p);
    }
}
