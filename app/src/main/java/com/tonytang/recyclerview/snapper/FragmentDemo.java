package com.tonytang.recyclerview.snapper;

import static com.tonytang.recyclerview.snapper.SnapBehavior.NOTIFY_ON_SCROLL_STATE_IDLE;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class FragmentDemo extends Fragment implements OnClickListener,
    OnSnapPositionChangeListener {

  private RecyclerView recycler_view;
  private TextView tv_selected_item;
  private SimplePickerAdapter adapter;
  private final SelectedPositionStream selectedPositionStream = new SelectedPositionStream();

  @Nullable
  @Override
  public View onCreateView(
      @NonNull LayoutInflater inflater,
      @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    View rootView = inflater.inflate(R.layout.fragment_demo, container, false);
    recycler_view = rootView.findViewById(R.id.recycler_view);
    tv_selected_item = rootView.findViewById(R.id.tv_selected_item);
    initRecyclerView();
    return rootView;
  }

  public void initRecyclerView() {
    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(requireContext());
    recycler_view.setLayoutManager(linearLayoutManager);
    adapter = new SimplePickerAdapter(requireContext(), params(), this, selectedPositionStream);
    recycler_view.setAdapter(adapter);
    final LinearSnapHelper snapHelper = new LinearSnapHelper();
    snapHelper.attachToRecyclerView(recycler_view);
    SnapOnScrollListener listener = new SnapOnScrollListener(snapHelper,
        NOTIFY_ON_SCROLL_STATE_IDLE, this);
    recycler_view.addOnScrollListener(listener);
  }

  private void newValueSelected(String data) {
    tv_selected_item.setText(SelectedTextBuilder.buildSelectedText(data));
  }

  private List<String> params() {
    List<String> result = new ArrayList<>();
    for (int i = 0; i < 10; i++) {
      result.add("Tier " + i);
    }
    return result;
  }

  @Override
  public void onClick(View view) {
    recycler_view.scrollToPosition((int) view.getTag());
  }

  @Override
  public void onSnapPositionChange(int position) {
    selectedPositionStream.setSelectedPosition(position);
    newValueSelected(adapter.getData(position));
    adapter.notifyItemChanged(position);
  }
}
