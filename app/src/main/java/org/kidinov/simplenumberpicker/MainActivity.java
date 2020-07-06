package org.kidinov.simplenumberpicker;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import org.kidinov.snp_lib.SimpleNumberPicker;


public class MainActivity extends AppCompatActivity {

  private TextView tv_selected_item;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    tv_selected_item = findViewById(R.id.tv_selected_item);
    ((SimpleNumberPicker) findViewById(R.id.picker)).setOnNewValueSelectedListener(this::bind);
  }

  private void bind(int value) {
    tv_selected_item.setText(String.valueOf(value));
  }

}