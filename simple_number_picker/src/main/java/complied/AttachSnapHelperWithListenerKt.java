package complied;

import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.OnScrollListener;
import androidx.recyclerview.widget.SnapHelper;
import complied.SnapOnScrollListener.Behavior;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

public final class AttachSnapHelperWithListenerKt {

  public static final void attachSnapHelperWithListener(
      @NotNull RecyclerView $this$attachSnapHelperWithListener, @NotNull SnapHelper snapHelper,
      @NotNull Behavior behavior,
      @NotNull OnSnapPositionChangeListener onSnapPositionChangeListener) {
    Intrinsics.checkParameterIsNotNull($this$attachSnapHelperWithListener,
        "$this$attachSnapHelperWithListener");
    Intrinsics.checkParameterIsNotNull(snapHelper, "snapHelper");
    Intrinsics.checkParameterIsNotNull(behavior, "behavior");
    Intrinsics
        .checkParameterIsNotNull(onSnapPositionChangeListener, "onSnapPositionChangeListener");
    snapHelper.attachToRecyclerView($this$attachSnapHelperWithListener);
    SnapOnScrollListener snapOnScrollListener = new SnapOnScrollListener(snapHelper, behavior,
        onSnapPositionChangeListener);
    $this$attachSnapHelperWithListener.addOnScrollListener((OnScrollListener) snapOnScrollListener);
  }

  // $FF: synthetic method
  public static void attachSnapHelperWithListener$default(RecyclerView var0, SnapHelper var1,
      Behavior var2, OnSnapPositionChangeListener var3, int var4, Object var5) {
    if ((var4 & 2) != 0) {
      var2 = Behavior.NOTIFY_ON_SCROLL;
    }

    attachSnapHelperWithListener(var0, var1, var2, var3);
  }
}
