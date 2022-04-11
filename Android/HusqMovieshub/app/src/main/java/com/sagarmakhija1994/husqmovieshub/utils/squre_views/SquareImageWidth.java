package com.sagarmakhija1994.husqmovieshub.utils.squre_views;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

@SuppressLint("AppCompatCustomView")
public class SquareImageWidth extends ImageView {
    public SquareImageWidth(Context context) {
        super(context);
    }
    public SquareImageWidth(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SquareImageWidth(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int Actulewidth = getMeasuredWidth();
        int width =(int)(Actulewidth*(100.0f/100.0f));

        int height = width;
        setMeasuredDimension(width , height);
    }
}