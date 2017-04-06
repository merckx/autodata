package com.android.commons.orientation;

import android.content.Context;
import android.content.res.Configuration;

/**
 * Created by yani on 27.2.2017 г..
 */

public class OrientationUtil {

    public static boolean isLandscape(Context context) {
        return getOrientation(context) == Configuration.ORIENTATION_LANDSCAPE;
    }

    public static int getOrientation(Context context)
    {
        return context.getResources().getConfiguration().orientation;
    }
}

