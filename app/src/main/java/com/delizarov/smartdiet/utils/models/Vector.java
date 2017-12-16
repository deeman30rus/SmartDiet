package com.delizarov.smartdiet.utils.models;


public class Vector {

    private double mX;
    private double mY;

    public Vector(double x, double y) {
        this.mX = x;
        this.mY = y;
    }

    public double magnitude() {

        return Math.sqrt(mX * mX + mY * mY);
    }

    public static double angleCosine(Vector v1, Vector v2) {

        return (v1.mX * v2.mX + v1.mY * v2.mY) / (v1.magnitude() * v2.magnitude());
    }
}
