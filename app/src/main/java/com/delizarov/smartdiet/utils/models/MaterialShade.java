package com.delizarov.smartdiet.utils.models;


public enum MaterialShade {

    Red500(0xF44336),
    Pink500(0xE91E63),
    Purple500(0x9C27B0),
    DeepPurple500(0x673AB7),
    Indigo500(0x3F51B5),
    Blue500(0x2196F3),
    lightBlue500(0x03A9F4),
    Cyan500(0x00BCD4),
    Teal500(0x009688),
    Green500(0x4CAF50),
    LightGreen500(0x8BC34A),
    Lime500(0xCDDC39),
    Yellow500(0xFFEB3B),
    Amber500(0xFFC107),
    Orange500(0xFF9800),
    DeepOrange500(0xFF5722),
    Brown500(0x795548);

    private Vector mVector;
    private int mColor;

    MaterialShade(int color) {

        mColor = color;
        mVector = fromColor(color);
    }

    public int getColor() {

        return mColor;
    }

    public static MaterialShade nearest(int color) {

        MaterialShade result = Red500;

        Vector ref = fromColor(color);

        double max = Vector.angleCosine(result.mVector, ref);

        for (int i = 1; i < values().length; ++i) {

            MaterialShade shade = values()[i];

            double cos = Vector.angleCosine(shade.mVector, ref);

            if (max < cos) {
                result = shade;
                max = cos;
            }
        }

        return result;
    }

    private static Vector fromColor(int color) {

        final double COS30 = Math.sqrt(3) / 2;
        final double SIN30 = 0.5;

        int red = (color & 0xff0000) >> 16;
        int green = (color & 0xff00) >> 8;
        int blue = (color & 0xff);

        double x = (red - blue) * COS30;
        double y = green - (red + blue) * SIN30;

        return new Vector(x, y);
    }
}
