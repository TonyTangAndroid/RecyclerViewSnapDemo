package complied;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.OnScrollListener;
import androidx.recyclerview.widget.SnapHelper;

public final class SnapOnScrollListener extends OnScrollListener {

  private int snapPosition;
  private final SnapHelper snapHelper;
  private SnapOnScrollListener.Behavior behavior;
  private OnSnapPositionChangeListener onSnapPositionChangeListener;

  public SnapOnScrollListener(SnapHelper snapHelper,
      @NonNull SnapOnScrollListener.Behavior behavior,
      @NonNull OnSnapPositionChangeListener onSnapPositionChangeListener) {
    this.snapHelper = snapHelper;
    this.behavior = behavior;
    this.onSnapPositionChangeListener = onSnapPositionChangeListener;
    this.snapPosition = RecyclerView.NO_POSITION;
  }


  public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
    if (this.behavior == SnapOnScrollListener.Behavior.NOTIFY_ON_SCROLL) {
      this.maybeNotifySnapPositionChange(recyclerView);
    }

  }

  public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
    if (this.behavior == SnapOnScrollListener.Behavior.NOTIFY_ON_SCROLL_STATE_IDLE
        && newState == RecyclerView.SCROLL_STATE_IDLE) {
      this.maybeNotifySnapPositionChange(recyclerView);
    }

  }

  private void maybeNotifySnapPositionChange(RecyclerView recyclerView) {
    int snapPosition = SnapPositionHelper.getSnapPosition(this.snapHelper, recyclerView);
    boolean snapPositionChanged = this.snapPosition != snapPosition;
    if (snapPositionChanged) {
      this.onSnapPositionChangeListener.onSnapPositionChange(snapPosition);
      this.snapPosition = snapPosition;
    }
  }

  public enum Behavior {
    NOTIFY_ON_SCROLL,
    NOTIFY_ON_SCROLL_STATE_IDLE;
  }
}