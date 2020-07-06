package org.kidinov.snp_lib;

import android.graphics.Typeface;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

public class ItemViewHolder extends RecyclerView.ViewHolder {

  private TextView textView;
  private Params params;

  public ItemViewHolder(View v, Params params) {
    super(v);
    this.params = params;
    RelativeLayout view = (RelativeLayout) v;
    textView = view.findViewById(R.id.text);
  }

  public void bind(int index) {
    index += params.getMin();
    if (index % params.getDelimNumber() != 0) {
      textView.setText(String.valueOf(index));
      textView.setTextSize(params.getSmallTextSize());
      textView.setTypeface(textView.getTypeface(), Typeface.NORMAL);
      textView.setTextColor(params.getSmallTextColor());
    } else {
      textView.setText(String.valueOf(index));
      textView.setTextSize(params.getBigTextSize());
      textView.setTypeface(textView.getTypeface(), Typeface.BOLD);
      textView.setTextColor(params.getBigTextColor());
    }
  }
}
