package com.delizarov.smartdiet.utils;


import android.content.Context;
import android.content.res.Resources;

import com.delizarov.smartdiet.R;

public class StringResourceUtils {

    public static String getMinutesString(Context context, int minutes) {

        Resources resources = context.getResources();

        return resources.getQuantityString(R.plurals.minutes, minutes, minutes);
    }

    public static String getCaloriesString(Context context, int calories) {

        Resources resources = context.getResources();

        return resources.getString(R.string.calories, calories);
    }
}
