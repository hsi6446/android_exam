package com.example.android.android_exam.graphic;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.android.android_exam.R;
import com.example.android.android_exam.utils.network.storage.StorageUtil;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by student on 2015-09-17.
 */
public class GraphicActivity extends AppCompatActivity {

    private static final String TAG = GraphicActivity.class.getSimpleName();
    private ShapeView mShapeView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mShapeView = new ShapeView(this);
        setContentView(mShapeView);

    }

    // 메뉴 생성부분
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_main, menu);

        return true;

    }

    // 메뉴 클릭했을 때 처리
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_save :
                save();
                return true;

            default:
                return super.onOptionsItemSelected(item);

        }

    }

    private void save() {
        Toast.makeText(GraphicActivity.this, "save", Toast.LENGTH_SHORT).show();

        mShapeView.setDrawingCacheEnabled(true);
        Bitmap bitmap = Bitmap.createBitmap(mShapeView.getDrawingCache());
        mShapeView.setDrawingCacheEnabled(false);

        // 외부 저장소에 접근이 가능하면, 파일 생성
        if (StorageUtil.isExternalStorageWritable()) {

            File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM)
                    , "pictureTest.jpg");

            FileOutputStream fos = null;
            try {

                fos = new FileOutputStream(file);
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
                fos.flush();
                Toast.makeText(GraphicActivity.this, "저장되었습니다.", Toast.LENGTH_SHORT).show();

            } catch (FileNotFoundException e) {
                Log.e(TAG, e.getMessage());
            } catch (IOException e) {
                Log.e(TAG, e.getMessage());
            }

            // 미디어 스캔하도록 브로드캐스트 발송 - 갤러리에서 보임
            sendBroadcast(new Intent(
                    Intent.ACTION_MEDIA_SCANNER_SCAN_FILE,
                    Uri.parse("fill://" + file.toString())
            ));


        }else{
            Toast.makeText(GraphicActivity.this, "메모리를 사용할 수 없습니다.", Toast.LENGTH_SHORT).show();
        }

    }
}
