package com.example.android.android_exam.graphic;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by student on 2015-09-17.
 */
public class ShapeView extends View {

    float mX;
    float mY;

    private Path mPath;
    private Paint mPaint;
    private List<Path> mList;

    // 속도를 보장해줌.
    private Bitmap mBitmap;
    private Canvas mCanvas;

    // code
    public ShapeView(Context context) {
        this(context, null);
    }

    // xml
    public ShapeView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    // xml 에서 넘어 온 속성을 멤버변수로 셋팅.
    public ShapeView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        mPath = new Path();
        mPaint = new Paint();

        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(10);

        mList = new ArrayList<>();

        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
                Display display = wm.getDefaultDisplay();
                Point size = new Point();
                display.getSize(size);
                int width = size.x;
                int height = size.y;

        mBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        mCanvas = new Canvas(mBitmap);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (mBitmap != null) {
            canvas.drawBitmap(mBitmap, 0, 0, null);
        }
        canvas.drawPath(mPath, mPaint);

    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();

        switch (action) {
            case MotionEvent.ACTION_DOWN:
                mX = event.getX();
                mY = event.getY();
                mPath.moveTo(mX, mY);
                break;
            case MotionEvent.ACTION_MOVE:
                mPath.quadTo(mX, mY, event.getX(), event.getY());
                mX = event.getX();
                mY = event.getY();
                break;
            case MotionEvent.ACTION_UP:
                mCanvas.drawPath(mPath, mPaint);
                mPath.rewind();
                break;
        }
        // 전체를 다시 그리기.
        invalidate();
        return true;
    }
}
