package com.milkzz.android_ui.ui1;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.milkzz.android_ui.R;

import java.util.ArrayList;
import java.util.List;

public class U1Activity extends AppCompatActivity implements View.OnClickListener {

    private static final int COUNT = 4;
    private static final int CIRCLE_R = 300;

    private Button btMain;
    private Button btItem1;
    private Button btItem2;
    private Button btItem3;
    private Button btItem4;

    private AnimatorSet mAnimatorSet;

    private List<AnimatorSet> setsOpen;
    private List<AnimatorSet> setsClose;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_u1);

        btMain = findViewById(R.id.bt1_activity_u1);
        btMain.setOnClickListener(this);
        btItem1 = findViewById(R.id.bt2_activity_u1);
        btItem1.setOnClickListener(this);
        btItem2 = findViewById(R.id.bt3_activity_u1);
        btItem2.setOnClickListener(this);
        btItem3 = findViewById(R.id.bt4_activity_u1);
        btItem3.setOnClickListener(this);
        btItem4 = findViewById(R.id.bt5_activity_u1);
        btItem4.setOnClickListener(this);

        setsOpen = new ArrayList<>();
        setsClose = new ArrayList<>();
        mAnimatorSet = new AnimatorSet();

        doOpen(btItem1,0);
        doOpen(btItem2,1);
        doOpen(btItem3,2);
        doOpen(btItem4,3);

        doClose(btItem1,0);
        doClose(btItem2,1);
        doClose(btItem3,2);
        doClose(btItem4,3);
    }

    private void doOpen(Button bt, int index) {

        double dg = Math.toRadians(90) / (COUNT - 1) * index;

        int x = -(int) (Math.sin(dg)* CIRCLE_R);
        int y = -(int) (Math.cos(dg) * CIRCLE_R);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(
                ObjectAnimator.ofFloat(bt, "scaleX", 0f, 1f),
                ObjectAnimator.ofFloat(bt, "scaleY", 0f, 1f),
                ObjectAnimator.ofFloat(bt, "translationX", 0, x),
                ObjectAnimator.ofFloat(bt, "translationY", 0, y),
                ObjectAnimator.ofFloat(bt,"alpha",0,1)
        );
        animatorSet.setDuration(500);
        setsOpen.add(animatorSet);
    }

    private void doClose(Button bt,int index){
        double dg = Math.toRadians(90) / (COUNT - 1) * index;

        int x = -(int) (Math.sin(dg)* CIRCLE_R);
        int y = -(int) (Math.cos(dg) * CIRCLE_R);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(
                ObjectAnimator.ofFloat(bt, "scaleX", 1f, 0.1f),
                ObjectAnimator.ofFloat(bt, "scaleY", 1f, 0.1f),
                ObjectAnimator.ofFloat(bt, "translationX", x, 0),
                ObjectAnimator.ofFloat(bt, "translationY", y, 0),
                ObjectAnimator.ofFloat(bt,"alpha",1,0)
        );
        animatorSet.setDuration(500);
        setsClose.add(animatorSet);
    }

    private void startAnim(){
        btItem1.setVisibility(View.VISIBLE);
        btItem2.setVisibility(View.VISIBLE);
        btItem3.setVisibility(View.VISIBLE);
        btItem4.setVisibility(View.VISIBLE);
        for(AnimatorSet animatorSet:setsOpen){
            if (animatorSet != null){
                animatorSet.start();
            }
        }
    }

    private void stopAnim(){
        for(AnimatorSet animatorSet:setsClose){
            if (animatorSet != null){
                animatorSet.start();
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bt1_activity_u1:{
                startAnim();
            }break;
            default:{
                stopAnim();
            }
        }
    }
}
