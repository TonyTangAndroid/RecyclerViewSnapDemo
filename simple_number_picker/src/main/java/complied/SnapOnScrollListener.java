package complied;

import static complied.SnapOnScrollListener.Behavior.NOTIFY_ON_SCROLL;
import static complied.SnapOnScrollListener.Behavior.NOTIFY_ON_SCROLL_STATE_IDLE;

import android.view.View;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.LayoutManager;
import androidx.recyclerview.widget.RecyclerView.OnScrollListener;
import androidx.recyclerview.widget.SnapHelper;

public final class SnapOnScrollListener extends OnScrollListener {

  private int snapPosition;
  private final SnapHelper snapHelper;
  private final SnapOnScrollListener.Behavior behavior;
  private final OnSnapPositionChangeListener onSnapPositionChangeListener;

  public SnapOnScrollListener(SnapHelper snapHelper,
      @NonNull SnapOnScrollListener.Behavior behavior,
      @NonNull OnSnapPositionChangeListener onSnapPositionChangeListener) {
    this.snapHelper = snapHelper;
    this.behavior = behavior;
    this.onSnapPositionChangeListener = onSnapPositionChangeListener;
    this.snapPosition = RecyclerView.NO_POSITION;
  }


  public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
    if (this.behavior == NOTIFY_ON_SCROLL) {
      this.maybeNotifySnapPositionChange(recyclerView);
    }
  }

  public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
    if (this.behavior == NOTIFY_ON_SCROLL_STATE_IDLE
        && newState == RecyclerView.SCROLL_STATE_IDLE) {
      this.maybeNotifySnapPositionChange(recyclerView);
    }

  }

  private void maybeNotifySnapPositionChange(RecyclerView recyclerView) {
    int snapPosition;
    LayoutManager layoutManager = recyclerView.getLayoutManager();
    if (layoutManager != null) {
      View snapView = this.snapHelper.findSnapView(layoutManager);
      if (snapView != null) {
        snapPosition = layoutManager.getPosition(snapView);
      } else {
        snapPosition = RecyclerView.NO_POSITION;
      }
    } else {
      snapPosition = RecyclerView.NO_POSITION;
    }
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