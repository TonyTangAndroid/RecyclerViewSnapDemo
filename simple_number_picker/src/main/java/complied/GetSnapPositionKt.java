package complied;

import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.LayoutManager;
import androidx.recyclerview.widget.SnapHelper;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

public final class GetSnapPositionKt {
   public static final int getSnapPosition(@NotNull SnapHelper $this$getSnapPosition, @NotNull RecyclerView recyclerView) {
      Intrinsics.checkParameterIsNotNull($this$getSnapPosition, "$this$getSnapPosition");
      Intrinsics.checkParameterIsNotNull(recyclerView, "recyclerView");
      LayoutManager var10000 = recyclerView.getLayoutManager();
      if (var10000 != null) {
         Intrinsics.checkExpressionValueIsNotNull(var10000, "recyclerView.layoutManag… RecyclerView.NO_POSITION");
         LayoutManager layoutManager = var10000;
         View var4 = $this$getSnapPosition.findSnapView(layoutManager);
         if (var4 != null) {
            Intrinsics.checkExpressionValueIsNotNull(var4, "findSnapView(layoutManag… RecyclerView.NO_POSITION");
            View snapView = var4;
            return layoutManager.getPosition(snapView);
         } else {
            return -1;
         }
      } else {
         return -1;
      }
   }
}