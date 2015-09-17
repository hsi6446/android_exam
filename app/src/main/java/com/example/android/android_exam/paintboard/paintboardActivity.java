
package com.example.android.android_exam.paintboard;


import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;

import com.example.android.android_exam.R;

public class PaintBoardActivity extends Activity {

    private Paint mPaint;
    private Path mPath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
        setContentView(new FingerPaintView(this));
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);

        // 선 색깔 설정
        String color = "#E91E63";
        mPaint.setColor(Color.parseColor(color));

        // 선 끝 부분 모양.ROUND = 원
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeJoin(Paint.Join.ROUND);
        mPaint.setStrokeCap(Paint.Cap.ROUND);

        // 선 굵기
        mPaint.setStrokeWidth(5);

    }

    public class drawPoint {
        private float x;
        private float y;
        private boolean draw;

        public drawPoint(float x, float y, boolean d) {
            this.x = x;
            this.y = y;
            draw = d;
        }


        public boolean getDraw() {
            return draw;
        }

        public void setDraw(boolean _draw) {
            draw = _draw;
        }

        public float getX() {
            return x;
        }

        public float getY() {
            return y;
        }
    }

    public class FingerPaintView extends View {

        private static final float MINP = 0.25f;
        private static final float MAXP = 0.75f;

        private Bitmap mBitmap;
        private Bitmap bm;
        private Canvas mCanvas;
        private Paint mBitmapPaint;

        // 생성자
        public FingerPaintView(Context context) {
            super(context);

            // 배경 비트맵
            bm = BitmapFactory.decodeResource(getResources(),
                    R.drawable.leva2);

            DisplayMetrics metrics = new DisplayMetrics();
            getWindowManager().getDefaultDisplay().getMetrics(metrics);

            // 가로, 세로, 컬러 품질(ARGB_8888이 제일 좋은 것.) ---아래가 기본 형태임.
            mBitmap = Bitmap.createBitmap(metrics.widthPixels,
                    metrics.heightPixels, Bitmap.Config.ARGB_8888);

            // 캔버스에 비트맵을 붙여줌.
            mCanvas = new Canvas(mBitmap);
            mPath = new Path();
            mBitmapPaint = new Paint(Paint.DITHER_FLAG);
            // Matrix matrix;
            // mCanvas.setBitmap(bm);
            // mCanvas.drawBitmap(bm, matrix, 0xFFFFFFFF);

            // 0xFFFFFFFF = White
            mCanvas.drawColor(0xFFFFFFFF); // backgroundcolor
        }

        protected void onDraw(Canvas canvas) {
            // canvas.drawColor(0x00000000);

            canvas.drawBitmap(mBitmap, 0, 0, null); // 배경바꾸기 - mBitmap 부분 change
            canvas.drawPath(mPath, mPaint);
        }

        /**
         */
        private float mX, mY;
        private static final float TOUCH_TOLERANCE = 4;

        /**
         */
        private void touch_start(float x, float y) {
            // mPath.reset();
            mPath.moveTo(x, y);
            mX = x;
            mY = y;
        }

        /**
         */
        private void touch_move(float x, float y) {
            float dx = Math.abs(x - mX);
            float dy = Math.abs(y - mY);
            if (dx >= TOUCH_TOLERANCE || dy >= TOUCH_TOLERANCE) {
                mPath.quadTo(mX, mY, (x + mX) / 2, (y + mY) / 2);
                mX = x;
                mY = y;
            }
        }

        private void touch_up() {
            mPath.lineTo(mX, mY);
            mCanvas.drawPath(mPath, mPaint);
            // mPath.rewind();
        }

        public boolean onTouchEvent(MotionEvent event) {
            int action = event.getAction();
            float x = event.getX();
            float y = event.getY();
            drawPoint p = new drawPoint(x, y, false);
            switch (action) {
                case MotionEvent.ACTION_DOWN:
                    touch_start(x, y);
                    break;
                case MotionEvent.ACTION_MOVE:
                    touch_move(x, y);
                    break;
                case MotionEvent.ACTION_UP:
                    touch_up();
                    break;
            }
            invalidate();
            return true;
        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // TODO Auto-generated method stub
        super.onCreateOptionsMenu(menu);
        MenuItem item = menu.add(0, 1, 0, "다시쓰기");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // TODO Auto-generated method stub
        super.onOptionsItemSelected(item);

        switch (item.getItemId()) {
            case 1:
                mPath.reset();
                onResume();
        }

        return true;
    }


}
