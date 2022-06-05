package com.example.prepitbackend.utils;

public class Scaling {
    public static Double minMaxScaling(Integer min, Integer max, Integer value) {
        double minimum = (double) min;
        double maximum = (double) max;
        double inputValue = (double) value;
        double result = (double) ((inputValue - minimum)/(maximum - minimum));
        return result;
    }

    public static Double logScaling(Integer value) {
        return Math.log(value);
    }

    public static Double featureClipping(Integer min, Integer max, Integer value) {
        if (value < min) {
            return (double) min;
        }

        if (value > max) {
            return (double) max;
        }

        return (double) value;
    }
}
