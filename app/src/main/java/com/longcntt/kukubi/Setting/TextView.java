package com.longcntt.kukubi.Setting;

import android.content.Context;
import android.util.AttributeSet;

public class TextView extends android.support.v7.widget.AppCompatTextView {
    public TextView(Context context) {
        super(context);
    }

    public TextView(Context context,  AttributeSet attrs) {
        super(context, attrs);
    }

    public TextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int width = getMeasuredWidth();
        setMeasuredDimension(width, width);
    }
}
