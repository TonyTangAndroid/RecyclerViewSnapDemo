package complied;

import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;
import complied.SnapOnScrollListener.Behavior;

public final class AttachSnapHelperWithListener {

  public static void attachSnapHelperWithListener(
      RecyclerView view, SnapHelper snapHelper,
      Behavior behavior,
      OnSnapPositionChangeListener onSnapPositionChangeListener) {
    snapHelper.attachToRecyclerView(view);
    SnapOnScrollListener snapOnScrollListener = new SnapOnScrollListener(snapHelper, behavior,
        onSnapPositionChangeListener);
    view.addOnScrollListener(snapOnScrollListener);
  }

}
