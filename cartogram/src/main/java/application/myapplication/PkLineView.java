package application.myapplication;

import android.content.Context;
import android.graphics.Canvas;
import android.os.Build;
import android.util.AttributeSet;
import android.view.ViewGroup;

/**
 * Created by 78101 on 2016/9/29.
 */
public class PkLineView extends ViewGroup {
    private PKLineBGView pkLineBGView;
    private int width;
    private int height;

    public PkLineView(Context context) {
        super(context);
        initView(context);
    }

    public PkLineView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }


    private void initView(Context context) {
        setWillNotDraw(false);
        pkLineBGView=new PKLineBGView(context);
        addView(pkLineBGView);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        measureChildren(widthMeasureSpec,heightMeasureSpec);
        width = getMeasuredWidth();
        height = getMeasuredHeight();
    }


    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            pkLineBGView.setElevation(20f);
        }
       LayoutParams cParams = new LayoutParams(width,height/2);
        pkLineBGView.setLayoutParams(cParams);
        pkLineBGView.layout(l, t*1/4, r, b*3/4);

    }

    public PkLineView setSize(int i, int i1) {
         pkLineBGView.setSize(i,i1);
        return this;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        pkLineBGView.draw(canvas);
    }


    public void finish(){
        invalidate();
        pkLineBGView.invalidate();
    }

}
