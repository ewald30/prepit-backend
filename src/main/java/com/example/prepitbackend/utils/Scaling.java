package com.example.prepitbackend.utils;

public class Scaling {
    public static Double minMaxScaling(Integer min, Integer max, Integer value) {
        return (double) ((value - min)/(max - min));
    }

    public static Double logScaling(Integer value) {
        return Math.log(value);
    }
}
