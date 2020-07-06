package complied;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.OnScrollListener;
import androidx.recyclerview.widget.SnapHelper;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

public final class SnapOnScrollListener extends OnScrollListener {

  private int snapPosition;
  private final SnapHelper snapHelper;
  @NotNull
  private SnapOnScrollListener.Behavior behavior;
  @Nullable
  private OnSnapPositionChangeListener onSnapPositionChangeListener;

  public void onScrolled(@NotNull RecyclerView recyclerView, int dx, int dy) {
    Intrinsics.checkParameterIsNotNull(recyclerView, "recyclerView");
    if (this.behavior == SnapOnScrollListener.Behavior.NOTIFY_ON_SCROLL) {
      this.maybeNotifySnapPositionChange(recyclerView);
    }

  }

  public void onScrollStateChanged(@NotNull RecyclerView recyclerView, int newState) {
    Intrinsics.checkParameterIsNotNull(recyclerView, "recyclerView");
    if (this.behavior == SnapOnScrollListener.Behavior.NOTIFY_ON_SCROLL_STATE_IDLE
        && newState == 0) {
      this.maybeNotifySnapPositionChange(recyclerView);
    }

  }

  private final void maybeNotifySnapPositionChange(RecyclerView recyclerView) {
    int snapPosition = GetSnapPositionKt.getSnapPosition(this.snapHelper, recyclerView);
    boolean snapPositionChanged = this.snapPosition != snapPosition;
    if (snapPositionChanged) {
      OnSnapPositionChangeListener var10000 = this.onSnapPositionChangeListener;
      if (var10000 != null) {
        var10000.onSnapPositionChange(snapPosition);
      }

      this.snapPosition = snapPosition;
    }

  }

  @NotNull
  public final SnapOnScrollListener.Behavior getBehavior() {
    return this.behavior;
  }

  public final void setBehavior(@NotNull SnapOnScrollListener.Behavior var1) {
    Intrinsics.checkParameterIsNotNull(var1, "<set-?>");
    this.behavior = var1;
  }

  @Nullable
  public final OnSnapPositionChangeListener getOnSnapPositionChangeListener() {
    return this.onSnapPositionChangeListener;
  }

  public final void setOnSnapPositionChangeListener(@Nullable OnSnapPositionChangeListener var1) {
    this.onSnapPositionChangeListener = var1;
  }

  public SnapOnScrollListener(@NotNull SnapHelper snapHelper,
      @NotNull SnapOnScrollListener.Behavior behavior,
      @Nullable OnSnapPositionChangeListener onSnapPositionChangeListener) {
    Intrinsics.checkParameterIsNotNull(snapHelper, "snapHelper");
    Intrinsics.checkParameterIsNotNull(behavior, "behavior");
    this.snapHelper = snapHelper;
    this.behavior = behavior;
    this.onSnapPositionChangeListener = onSnapPositionChangeListener;
    this.snapPosition = -1;
  }


  public enum Behavior {
    NOTIFY_ON_SCROLL,
    NOTIFY_ON_SCROLL_STATE_IDLE;
  }
}