package com.milkzz.android_ui.ui2;

import android.animation.Keyframe;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.TypeEvaluator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.menu.ActionMenuItemView;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.widget.TextView;

import com.milkzz.android_ui.R;

public class UI2Activity extends AppCompatActivity {

    MyTextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ui2);

        tv = findViewById(R.id.text);

        PropertyValuesHolder holder = PropertyValuesHolder.ofObject("CharText",new charEvaluator(),'A','Z');
        final ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(tv,holder);
        animator.setDuration(1000);
        animator.setInterpolator(new AccelerateInterpolator());

        Keyframe keyframe1 = Keyframe.ofFloat(0f,0f);
        Keyframe keyframe2 = Keyframe.ofFloat(0.2f,-20f);
        Keyframe keyframe3 = Keyframe.ofFloat(1,0);

        PropertyValuesHolder holder1 = PropertyValuesHolder.ofKeyframe("rotation",keyframe1,keyframe2,keyframe3);
        final ObjectAnimator animator1 = ObjectAnimator.ofPropertyValuesHolder(tv,holder1);
        animator1.setDuration(2000);


        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animator1.start();
            }
        });




    }

    class charEvaluator implements TypeEvaluator<Character>{

        @Override
        public Character evaluate(float fraction, Character startValue, Character endValue) {
            int st = startValue;
            int et = endValue;
            int c = (int) (st + fraction * (et - st));
            return (char)c;
        }
    }
}
