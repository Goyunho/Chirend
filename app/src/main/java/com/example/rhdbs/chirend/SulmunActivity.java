package com.example.rhdbs.chirend;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.NumberPicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

/**
 * Created by rhdbs on 2016-10-13.
 */

public class SulmunActivity extends AppCompatActivity {
    NumberPicker age_picker;
    RadioGroup gender_RG;
    int NPValue;
    String sex;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sulmun);
        setTitle("설문조사");

        age_picker = (NumberPicker) findViewById(R.id.age);
        age_picker.setMinValue(3);
        age_picker.setMaxValue(13);

        gender_RG = (RadioGroup) findViewById(R.id.sex);

    }

    private class RstType implements Runnable {
        public void run() {
            startActivity(new Intent(getApplication(), ShowTypeRst.class)); // 로딩이 끝난후 이동할 Activity
        }

    }

    public void showRst(View v) {
        Intent intent = new Intent(this, ShowTypeRst.class);
        NPValue = age_picker.getValue();
        intent.putExtra("age", Integer.toString(NPValue));
        RadioButton rb = (RadioButton) findViewById(gender_RG.getCheckedRadioButtonId());
        if(rb.getText().toString().equals("남자아이")){
            sex = "0";
        } else {
            sex = "1";
        }
        intent.putExtra("sex", sex);
        startActivity(intent);
    }
}
