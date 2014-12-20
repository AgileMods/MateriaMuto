package com.agilemods.materiamuto.common.core.helpers;

import java.util.Formatter;
import java.util.Locale;

public class Quaternion {

    public double x;
    public double y;
    public double z;
    public double s;

    public Quaternion() {
        s = 1.0D;
        x = 0.0D;
        y = 0.0D;
        z = 0.0D;
    }

    public Quaternion(Quaternion quaternion) {
        x = quaternion.x;
        y = quaternion.y;
        z = quaternion.z;
        s = quaternion.s;
    }

    public Quaternion(double d, double d1, double d2, double d3) {
        x = d1;
        y = d2;
        z = d3;
        s = d;
    }

    public void set(Quaternion quaternion) {
        x = quaternion.x;
        y = quaternion.y;
        z = quaternion.z;
        s = quaternion.s;
    }

    public static Quaternion aroundAxis(double ax, double ay, double az, double angle) {
        angle *= 0.5D;
        double d4 = Math.sin(angle);
        return new Quaternion(Math.cos(angle), ax * d4, ay * d4, az * d4);
    }

    public void multiply(Quaternion quaternion) {
        double d = s * quaternion.s - x * quaternion.x - y * quaternion.y - z * quaternion.z;
        double d1 = s * quaternion.x + x * quaternion.s - y * quaternion.z + z * quaternion.y;
        double d2 = s * quaternion.y + x * quaternion.z + y * quaternion.s - z * quaternion.x;
        double d3 = s * quaternion.z - x * quaternion.y + y * quaternion.x + z * quaternion.s;
        s = d;
        x = d1;
        y = d2;
        z = d3;
    }

    public void rightMultiply(Quaternion quaternion) {
        double d = s * quaternion.s - x * quaternion.x - y * quaternion.y - z * quaternion.z;
        double d1 = s * quaternion.x + x * quaternion.s + y * quaternion.z - z * quaternion.y;
        double d2 = s * quaternion.y - x * quaternion.z + y * quaternion.s + z * quaternion.x;
        double d3 = s * quaternion.z + x * quaternion.y - y * quaternion.x + z * quaternion.s;
        s = d;
        x = d1;
        y = d2;
        z = d3;
    }

    public double mag() {
        return Math.sqrt(x * x + y * y + z * z + s * s);
    }

    public void normalize() {
        double d = mag();
        if (d == 0.0D) {
            return;
        } else {
            d = 1.0D / d;
            x *= d;
            y *= d;
            z *= d;
            s *= d;
            return;
        }
    }

    public void rotate(Vector3 vec) {
        double d = -x * vec.x - y * vec.y - z * vec.z;
        double d1 = s * vec.x + y * vec.z - z * vec.y;
        double d2 = s * vec.y - x * vec.z + z * vec.x;
        double d3 = s * vec.z + x * vec.y - y * vec.x;
        vec.x = d1 * s - d * x - d2 * z + d3 * y;
        vec.y = d2 * s - d * y + d1 * z - d3 * x;
        vec.z = d3 * s - d * z - d1 * y + d2 * x;
    }

    @Override
    public String toString() {
        StringBuilder stringbuilder = new StringBuilder();
        Formatter formatter = new Formatter(stringbuilder, Locale.US);
        formatter.format("Quaternion:\n");
        formatter.format("  < %f %f %f %f >\n", s, x, y, z);
        formatter.close();
        return stringbuilder.toString();
    }

    public static Quaternion aroundAxis(Vector3 axis, double angle) {
        return aroundAxis(axis.x, axis.y, axis.z, angle);
    }

}
