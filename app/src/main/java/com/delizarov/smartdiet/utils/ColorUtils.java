package com.delizarov.smartdiet.utils;


import android.graphics.Bitmap;
import android.graphics.Color;
import android.support.v7.graphics.Palette;

public class ColorUtils {

    private static final int COLORS[] = {
            0xFFE57373, // red_300
            0xFFF44336, // red 500
            0xFFD32F2F, // red 700
            0xFFF06292, // pink 300
            0xFFE91E63, // pink 500
            0xFFC2185B, // pink 700
            0xFFBA68C8, // purple 300
            0xFF9C27B0, // purple 500
            0xFF7B1FA2, // purple 700
            0xFF9575CD, // deep purple 300
            0xFF673AB7, // deep purple 500
            0xFF512DA8, // deep purple 700
            0xFF7986CB, // indigo
            0xFF3F51B5,
            0xFF303F9F,
            0xFF64B5F6, // blue
            0xFF2196F3,
            0xFF1976D2,
            0xFF4FC3F7, // light blue
            0xFF03A9F4,
            0xFF0288D1,
            0xFF4DD0E1, // cyan
            0xFF00BCD4,
            0xFF0097A7,
            0xFF4DB6AC, // teal
            0xFF009688,
            0xFF00796B,
            0xFF81C784, // green
            0xFF4CAF50,
            0xFF388E3C,
            0xFFAED581, // light green
            0xFF8BC34A,
            0xFF689F38,
            0xFFDCE775, // lime
            0xFFCDDC39,
            0xFFAFB42B,
            0xFFFFF176, // yellow
            0xFFFFEB3B,
            0xFFFBC02D,
            0xFFFFD54F, // amber
            0xFFFFC107,
            0xFFFFA000,
            0xFFFFB74D, // orange
            0xFFFF9800,
            0xFFF57C00,
            0xFFFF8A65, // deep orange
            0xFFFF5722,
            0xFFE64A19
    };

    public static int decodeFromString(String str) {

        int index = str.hashCode() < 0 ? -str.hashCode() : str.hashCode();

        return COLORS[index % COLORS.length];
    }

    public static int nearestMaterialColor(Bitmap bmp) {

        Palette palette = Palette.generate(bmp);

        int color = palette.getVibrantColor(0);

        return nearestToShade500(color);
    }

    private static int nearestToShade500(int color) {

        double angles[] = new double[COLORS.length / 3];
        int colors[] = new int[COLORS.length / 3];


        double main = new Color(color).angle();

        for (int i = 1; i < COLORS.length; i += 3) {
            colors[i / 3] = COLORS[i];
            angles[i / 3] = new Color(COLORS[i]).angle() - main;
        }

        int min = 0;

        for (int i = 1; i < angles.length; ++i)
            if (angles[min] > angles[i])
                min = i;

        return colors[min];
    }

    private static class Color {

        private static final double COS30 = Math.sqrt(3) / 2;
        private static final double SIN30 = 0.5;

        private final int mRed;
        private final int mBlue;
        private final int mGreen;

        public Color(int color) {

            mRed = color & 0xff0000;
            mGreen = color & 0xff00;
            mBlue = color & 0xff;
        }

        public double angle() {

            double x = mRed * COS30 - mBlue * COS30;
            double y = mGreen - mRed * SIN30 - mBlue * SIN30;

            return Math.atan2(y, x);
        }
    }
}
