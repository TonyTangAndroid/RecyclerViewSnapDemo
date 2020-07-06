package org.kidinov.simplenumberpicker;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import org.kidinov.snp_lib.FragmentDemo;

public class MainActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    getSupportFragmentManager()
        .beginTransaction()
        .replace(R.id.fragment_demo, new FragmentDemo())
        .commit();
  }
}
