package com.milkzz.android_ui.ui2;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by zuoqi on 2019/11/4
 */
public class MyTextView extends android.support.v7.widget.AppCompatTextView {
    public MyTextView(Context context) {
        super(context);
    }

    public MyTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setCharText(Character charText){
        setText(String.valueOf(charText));
    }
}
