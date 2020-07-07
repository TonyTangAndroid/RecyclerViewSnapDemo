package com.tonytang.recyclerview.snapper;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class SimplePickerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

  private final Context context;
  private final List<String> list;
  private final OnClickListener onClickListener;
  private SelectedPositionStream selectedPositionStream;

  public SimplePickerAdapter(Context context, List<String> list,
      OnClickListener onClickListener,
      SelectedPositionStream selectedPositionStream) {
    this.context = context;
    this.list = list;
    this.onClickListener = onClickListener;
    this.selectedPositionStream = selectedPositionStream;
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
    itemViewHolder.itemView.setTag(position);
    itemViewHolder.itemView.setOnClickListener(onClickListener);
    itemViewHolder.bind(list.get(position), selected(position));
  }

  private boolean selected(int current) {
    int selectedPosition = selectedPositionStream.getSelectedPosition();
    System.out.println("selectedPosition:" + selectedPosition + ", current:" + current);
    return selectedPosition == current;
  }

  @Override
  public int getItemCount() {
    return list.size();
  }

  public String getData(int index) {
    return list.get(index);
  }
}
