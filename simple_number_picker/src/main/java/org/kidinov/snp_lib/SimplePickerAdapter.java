package org.kidinov.snp_lib;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;


public class SimplePickerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

  private Context context;
  private List<String> list;

  public SimplePickerAdapter(Context context, List<String> list) {
    this.context = context;
    this.list = list;
  }

  @NonNull
  @Override
  public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(context).inflate(R.layout.item_weight, parent, false);
    return new ItemViewHolder(view);

  }

  @Override
  public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
    ItemViewHolder itemViewHolder = (ItemViewHolder) holder;
    itemViewHolder.bind(list.get(position), selected(position));
  }

  private boolean selected(int position) {
    return position % 2 == 0;
  }

  @Override
  public int getItemCount() {
    return list.size();
  }

  public String getData(int index) {
    return list.get(index);
  }
}
