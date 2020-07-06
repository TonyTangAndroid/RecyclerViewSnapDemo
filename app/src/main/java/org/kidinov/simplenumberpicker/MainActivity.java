package org.kidinov.simplenumberpicker;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.TextView;

import org.kidinov.snp_lib.OnNewValueSelectedListener;
import org.kidinov.snp_lib.SimpleNumberPicker;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ((SimpleNumberPicker) findViewById(R.id.vertical_1)).setOnNewValueSelectedListener(new OnNewValueSelectedListener() {
            @Override
            public void newValueSelected(int value) {
                ((TextView) findViewById(R.id.vertical_1_v)).setText(String.valueOf(value));
            }
        });
    }

}
