package org.kidinov.snp_lib;

import android.content.Context;

/**
 * Created by akid on 30/04/15.
 */
public class EnvHelper {

  public static float pxFromDp(final Context context, final float dp) {
    return dp * context.getResources().getDisplayMetrics().density;
  }
}
