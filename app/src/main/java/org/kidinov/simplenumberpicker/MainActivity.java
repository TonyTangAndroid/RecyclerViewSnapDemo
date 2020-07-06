package org.kidinov.simplenumberpicker;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import org.kidinov.snp_lib.SimpleNumberPicker;


public class MainActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    ((SimpleNumberPicker) findViewById(R.id.vertical_1)).setOnNewValueSelectedListener(
        value -> ((TextView) findViewById(R.id.vertical_1_v)).setText(String.valueOf(value)));
  }

}
