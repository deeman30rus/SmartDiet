package com.delizarov.smartdiet.utils;


public class ColorUtils {

    private static final int COLORTS[] = {
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

        return COLORTS[str.hashCode() % COLORTS.length];
    }
}
