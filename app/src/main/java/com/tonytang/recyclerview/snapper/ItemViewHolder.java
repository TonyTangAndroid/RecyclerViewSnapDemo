package com.tonytang.recyclerview.snapper;

import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

public class ItemViewHolder extends RecyclerView.ViewHolder {

  private final TextView textView;

  public ItemViewHolder(View v) {
    super(v);
    RelativeLayout view = (RelativeLayout) v;
    textView = view.findViewById(R.id.text);
  }

  public void bind(String value, boolean selected) {
    textView.setText(selected ? SelectedTextBuilder.buildSelectedText(value) : value);
  }
}
