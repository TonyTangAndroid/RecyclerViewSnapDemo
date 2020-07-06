package org.kidinov.snp_lib;

import android.graphics.Typeface;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

public class ItemViewHolder extends RecyclerView.ViewHolder {

  private TextView textView;

  public ItemViewHolder(View v) {
    super(v);
    RelativeLayout view = (RelativeLayout) v;
    textView = view.findViewById(R.id.text);
  }

  public void bind(String value, boolean selected) {
    textView.setText(value);
    textView.setTypeface(textView.getTypeface(), selected ? Typeface.BOLD : Typeface.NORMAL);
  }
}
