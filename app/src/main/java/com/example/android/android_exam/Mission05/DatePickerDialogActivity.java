
package com.example.android.android_exam.Mission05;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.example.android.android_exam.R;

public class DatePickerDialogActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText mNameEdit;
    private EditText mAgeEdit;
    private Button mMonthYearButton;
    private Button mSaveButton;
    private String mInformation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date_picker_dialog);

        mNameEdit = (EditText) findViewById(R.id.name_edit_text);
        mAgeEdit = (EditText) findViewById(R.id.age_edit_text);
        mMonthYearButton = (Button) findViewById(R.id.ageYear_button);
        mSaveButton = (Button) findViewById(R.id.save_button);

        mMonthYearButton.setOnClickListener(this);
        mSaveButton.setOnClickListener(this);

    }

    private DatePickerDialog.OnDateSetListener listener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

            mInformation = year + "년" + (monthOfYear + 1) + "월" + dayOfMonth + "일";
            mMonthYearButton.setText(mInformation);

        }
    };

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ageYear_button:
                DatePickerDialog dialog = new DatePickerDialog(DatePickerDialogActivity.this,
                        listener, 1980, 1, 1);
                dialog.show();

                break;

            case R.id.save_button:
                Toast.makeText(DatePickerDialogActivity.this, mInformation, Toast.LENGTH_SHORT)
                        .show();
                break;
        }
    }
}
