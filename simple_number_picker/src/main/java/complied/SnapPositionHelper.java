package complied;

import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.LayoutManager;
import androidx.recyclerview.widget.SnapHelper;

public final class SnapPositionHelper {

  public static int getSnapPosition(SnapHelper snapHelper, RecyclerView recyclerView) {
    LayoutManager layoutManager = recyclerView.getLayoutManager();
    if (layoutManager != null) {
      View snapView = snapHelper.findSnapView(layoutManager);
      if (snapView != null) {
        return layoutManager.getPosition(snapView);
      } else {
        return RecyclerView.NO_POSITION;
      }
    } else {
      return RecyclerView.NO_POSITION;
    }
  }
}