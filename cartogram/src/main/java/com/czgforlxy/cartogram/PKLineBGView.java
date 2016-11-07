package com.czgforlxy.cartogram;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import java.text.DecimalFormat;

/**
 * Created by 78101 on 2016/9/29.
 */
public class PKLineBGView extends View {

    private float mLeftSize,mRightSize;
    private float mHight,mWigth;
    private Paint mLeftPaint,mRightPaint,mTextPaint;
    public PKLineBGView(Context context) {
        super(context);
        intitview();
    }
    public PKLineBGView(Context context, AttributeSet attrs) {
        super(context, attrs);
        intitview();
    }


    private void intitview() {
        mLeftPaint = new Paint();
        mRightPaint = new Paint();
        mTextPaint=new Paint();
        mLeftPaint.setColor(Color.parseColor("#f42f45"));
        mRightPaint.setColor(Color.parseColor("#2f7ef4"));
        mTextPaint.setColor(Color.parseColor("#ffffff"));

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mHight=getMeasuredHeight();
        mWigth=getMeasuredWidth();
        mTextPaint.setTextSize(mHight/2);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float red= mLeftSize  / (mLeftSize + mRightSize);
        float blue=1f-red;
        float left =red*mWigth;
        float right =mWigth-left;
        canvas.drawRect(0, 0,mWigth , mHight, mLeftPaint);
        canvas.drawRect(left, 0, mWigth, mHight, mRightPaint);
        DecimalFormat   fnum  =   new DecimalFormat("##0.0");
        String   leftText=fnum.format(red*100f);
        String   rightText=fnum.format(blue*100f);
        canvas.drawText(leftText+"%",left/2,mHight/3*2,mTextPaint);

      if(!((mWigth- left+right/2)<mHight*5/2)) {
          canvas.drawText(rightText+"%",left+right/2,mHight/3*2,mTextPaint);
      }

    }

    public PKLineBGView setSize(float leftSize, float rightSize){
        mLeftSize=leftSize;
        mRightSize=rightSize;
        return this;
    }


}
