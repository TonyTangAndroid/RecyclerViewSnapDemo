package org.kidinov.snp_lib;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


public class SimplePickerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

  private Params params;
  private LayoutInflater li;

  public SimplePickerAdapter(Context ctx, Params params) {
    this.params = params;
    li = LayoutInflater.from(ctx);
  }

  @NonNull
  @Override
  public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    return new ViewHolder(li.inflate(R.layout.weight_item, parent, false), params);

  }

  @Override
  public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int index) {
    ViewHolder h = (ViewHolder) holder;
    h.bind(index);
  }

  @Override
  public int getItemCount() {
    return params.getMax() - params.getMin();
  }

  public static class ViewHolder extends RecyclerView.ViewHolder {

    private TextView textView;
    private Params params;

    public ViewHolder(View v, Params params) {
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
}
