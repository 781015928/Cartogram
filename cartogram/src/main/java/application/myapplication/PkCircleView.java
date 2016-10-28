package application.myapplication;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import java.text.DecimalFormat;

/**
 * Created by 78101 on 2016/9/29.
 */
public class PkCircleView extends View {


    private  PkCircleViewAdapter adapter;
    private int height;
    private int width;
    private Paint circlePaint;
    private Paint textPaint;
    private Paint arcPaint;
    private Paint linePaint;
    private String[] strs=new String[]{"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R" +
            "S","T","U","V","W","X","Y","Z"};
    private Paint itemText;
    private Fanned[] fanneds;
    public PkCircleView(Context context) {
        super(context);
    }

    public PkCircleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    private void initView(Context context) {
        circlePaint = new Paint();
        textPaint = new Paint();
        arcPaint=new Paint();
        linePaint=new Paint();
        itemText =new Paint();

        circlePaint.setAntiAlias(true);
        textPaint.setAntiAlias(true);
        arcPaint.setAntiAlias(true);
        linePaint.setAntiAlias(true);
        itemText.setAntiAlias(true);
        circlePaint.setColor(Color.parseColor("#ffffff"));
        textPaint.setColor(Color.parseColor("#000000"));
        linePaint.setColor(Color.parseColor("#000000"));
        itemText.setColor(Color.parseColor("#000000"));

        linePaint.setStrokeWidth(3);
        linePaint.setTextSize(2);
        linePaint.setStyle(Paint.Style.STROKE);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        height = getMeasuredHeight();
        width = getMeasuredWidth();
        textPaint.setTextSize(height*4/50);
        itemText.setTextSize(height/20);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if(adapter!=null) {
            int count=0;
            fanneds=new Fanned[adapter.getCount()];
            for (int i=0;i<adapter.getCount();i++){
                if(fanneds[i]==null) {
                    fanneds[i]=new Fanned();
                }
                Fanned fanned = adapter.getFanned(fanneds[i], i);
                count=count+fanned.getSize();
            }
            float left = width/2-height*3/8;
            float top=height/8f;
            float right=width/2+height*3/8;
            float bottom=height-height/8f;

            RectF rectF = new RectF(left,top ,right ,bottom );
            Log.e("TAG", "----"+left+"----"+top+"----"+right+"----"+bottom);
            if(count==0) {
                return;
            }
            float startdegree=-90f;
            for (int i=0;i<adapter.getCount();i++){
                startdegree = drawArc(canvas, count, rectF, startdegree, i);
            }
            canvas.drawCircle(width/2,height/2,height/4,circlePaint);
            canvas.drawText("投票百分比",width/2-+height/5,height/2+height*4/50/2,textPaint);

           }

    }

    private void drawLine(Canvas canvas, int count, int startdegree, int i) {
        Fanned fanned = adapter.getFanned(new Fanned(), i);
        int degree = fanned.getSize() * 360 / count;
        int enterdegree = startdegree + degree / 2;
        Point point = getPoint(height,enterdegree,width/2,height/2);
        Path path=new Path();
        path.moveTo(width/2,height/2);
        path.lineTo(point.x,point.y);

        canvas.drawPath(path,linePaint);
    }

    /**
     * @param canvas 画布
     * @param count 总数量
     * @param rectF 扇形矩阵
     * @param startdegree 开始读书
     * @param i  下标
     * @return  下一次开始的度数
     */
    private float drawArc(Canvas canvas, int count, RectF rectF, float startdegree, int i) {
        Fanned fanned = adapter.getFanned(fanneds[i], i);
        arcPaint.setColor(fanned.getColor());
        float degree = fanned.getSize() * 360f / count;
        if(i==adapter.getCount()) {
            canvas.drawArc(rectF, startdegree, 360-startdegree, true, arcPaint);
        }else {
            canvas.drawArc(rectF, startdegree, degree, true, arcPaint);
        }




        float centerdegree=startdegree+degree/2;
        int radias = (int) (((rectF.right - rectF.left)/2-height / 4)/2+height / 4);//中心点到环中心的距离

        Point point1=getPoint(radias,centerdegree,width/2,height/2);

        Point point2=getPoint(height/2,centerdegree,width/2,height/2);

        Point point3= getNextPoint(point2);


        Path path=new Path();
        path.moveTo(point1.x,point1.y);

        path.lineTo(point2.x,point2.y);

        path.lineTo(point3.x,point3.y);

        canvas.drawPath(path,linePaint);
        DecimalFormat fnum  =   new DecimalFormat("##0.0");
        String   text=fnum.format(fanned.getSize()*100f/count);
        if(point3.y<height/20) {
            point3.y=height/20;
        }
        if(point3.x<width/2) {
            point3.x= point3.x - height * 7 / 20;
            if(point3.x<0) {
                point3.x=height  / 20;
            }
        }
        canvas.drawText(strs[i]+":"+text+"%",point3.x,point3.y,itemText);

        startdegree=startdegree+degree;
        return startdegree;
    }

    private Point getNextPoint(Point point2) {
        int x1;
        int y1=point2.y;
        if(point2.x>width/2){//右边
            x1=point2.x+(width-point2.x)/3;

        }else {

            x1=point2.x-(point2.x)/3;
          
        }

      return   new Point(x1,y1);
    }

    public Point getPoint(int radias, float degree,int  radiasX,int radiasY){
        int x1 = (int) (radiasX + radias * Math.cos(degree * Math.PI / 180f));
        int y1 = (int) (radiasY + radias * Math.sin(degree * Math.PI / 180f));
        return  new Point(x1,y1);
    }
    public PkCircleViewAdapter getAdapter() {
        return adapter;
    }

    public void setAdapter(PkCircleViewAdapter adapter) {
        this.adapter = adapter;
    }



}

