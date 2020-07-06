package org.kidinov.snp_lib;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
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
    return new ItemViewHolder(li.inflate(R.layout.weight_item, parent, false), params);

  }

  @Override
  public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int index) {
    ItemViewHolder h = (ItemViewHolder) holder;
    h.bind(index);
  }

  @Override
  public int getItemCount() {
    return params.getMax() - params.getMin();
  }

}
