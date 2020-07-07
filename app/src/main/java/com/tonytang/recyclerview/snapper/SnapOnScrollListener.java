package com.tonytang.recyclerview.snapper;

import static com.tonytang.recyclerview.snapper.SnapBehavior.NOTIFY_ON_SCROLL;
import static com.tonytang.recyclerview.snapper.SnapBehavior.NOTIFY_ON_SCROLL_STATE_IDLE;

import android.view.View;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.LayoutManager;
import androidx.recyclerview.widget.RecyclerView.OnScrollListener;
import androidx.recyclerview.widget.SnapHelper;

public final class SnapOnScrollListener extends OnScrollListener {

  private int currentPosition;

  private final SnapHelper snapHelper;
  private final SnapBehavior behavior;
  private final OnSnapPositionChangeListener listener;

  public SnapOnScrollListener(SnapHelper snapHelper,
      @NonNull SnapBehavior behavior,
      @NonNull OnSnapPositionChangeListener listener) {
    this.snapHelper = snapHelper;
    this.behavior = behavior;
    this.listener = listener;
    this.currentPosition = RecyclerView.NO_POSITION;
  }


  public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
    if (this.behavior == NOTIFY_ON_SCROLL) {
      maybeNotifySnapPositionChange(recyclerView);
    }
  }

  public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
    if (this.behavior == NOTIFY_ON_SCROLL_STATE_IDLE
        && newState == RecyclerView.SCROLL_STATE_IDLE) {
      maybeNotifySnapPositionChange(recyclerView);
    }

  }

  private void maybeNotifySnapPositionChange(RecyclerView recyclerView) {
    int snapPosition = currentSnappedPosition(recyclerView);
    boolean snapPositionChanged = this.currentPosition != snapPosition;
    if (snapPositionChanged) {
      this.listener.onSnapPositionChange(snapPosition);
      this.currentPosition = snapPosition;
    }
  }

  private int currentSnappedPosition(RecyclerView recyclerView) {
    LayoutManager layoutManager = recyclerView.getLayoutManager();
    if (layoutManager == null) {
      return RecyclerView.NO_POSITION;
    }
    final View snapView = this.snapHelper.findSnapView(layoutManager);
    return snapView != null ? layoutManager.getPosition(snapView) : RecyclerView.NO_POSITION;
  }

}