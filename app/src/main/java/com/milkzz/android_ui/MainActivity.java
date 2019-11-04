package com.milkzz.android_ui;

import android.animation.AnimatorInflater;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv = findViewById(R.id.text);
        final ValueAnimator animator = (ValueAnimator) AnimatorInflater.loadAnimator(this, R.animator.test1);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int off = (int) animation.getAnimatedValue();
                tv.layout(off, off, tv.getWidth() + off, tv.getHeight() + off);
            }
        });

        final ObjectAnimator objectAnimator = (ObjectAnimator) AnimatorInflater.loadAnimator(getBaseContext(),R.animator.test2);
        objectAnimator.setTarget(tv);

        PropertyValuesHolder rotationHolder = PropertyValuesHolder.ofFloat("Rotation",60f,-60f,40f,-40f,20f,-20f,0f);
        PropertyValuesHolder alphaHolder = PropertyValuesHolder.ofFloat("alpha",0.1f,1f,0.1f);
        final ObjectAnimator animator1 = (ObjectAnimator) ObjectAnimator.ofPropertyValuesHolder(tv,rotationHolder,alphaHolder);
        animator1.setDuration(3000);
//        animator1.setTarget(tv);

        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                animator.start();
//                objectAnimator.start();
                animator1.start();
            }
        });
    }
}
