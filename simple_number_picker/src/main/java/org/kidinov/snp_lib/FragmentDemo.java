package org.kidinov.snp_lib;

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
import complied.AttachSnapHelperWithListener;
import complied.OnSnapPositionChangeListener;
import complied.SnapOnScrollListener.Behavior;
import java.util.ArrayList;
import java.util.List;

public class FragmentDemo extends Fragment implements OnClickListener {

  private RecyclerView recycler_view;
  private TextView tv_selected_item;
  private LinearLayoutManager llm;

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
    llm = new LinearLayoutManager(requireContext());
    recycler_view.setLayoutManager(llm);
    SimplePickerAdapter adapter = new SimplePickerAdapter(requireContext(), params(),
        this);
    recycler_view.setAdapter(adapter);
    LinearSnapHelper snapHelper = new LinearSnapHelper();
    snapHelper.attachToRecyclerView(recycler_view);

    AttachSnapHelperWithListener.attachSnapHelperWithListener(recycler_view, snapHelper,
        Behavior.NOTIFY_ON_SCROLL_STATE_IDLE, new OnSnapPositionChangeListener() {
          @Override
          public void onSnapPositionChange(int position) {
            newValueSelected(adapter.getData(position));
          }
        });
  }

  private void newValueSelected(String data) {
    tv_selected_item.setText(data);
  }

  private List<String> params() {
    List<String> result = new ArrayList<>();
    for (int i = 0; i < 10; i++) {
      result.add("Tier" + i);
    }
    return result;
  }

  private int selectedIndex() {
    int last = llm.findLastCompletelyVisibleItemPosition();
    System.out.println("Last:" + last);
    if (last == llm.getItemCount() - 1) {
      return last;
    }
    int firstVisibleItemPosition = llm.findFirstVisibleItemPosition();
    int lastVisibleItemPosition = llm.findLastVisibleItemPosition();
    return firstVisibleItemPosition
        + (lastVisibleItemPosition - firstVisibleItemPosition) / 2;
  }

  @Override
  public void onClick(View view) {
    recycler_view.smoothScrollToPosition((int) view.getTag());
  }
}
