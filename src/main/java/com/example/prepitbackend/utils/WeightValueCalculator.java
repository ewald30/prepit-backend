package com.example.prepitbackend.utils;

public class WeightValueCalculator {
    public static int  calculate(int calories, int priceScore, int timeScore, Double timeScorePercent, Double priceScorePercent) {
        return (int) ((calories/10) + priceScorePercent * (priceScore*1) + timeScorePercent * (timeScore*1));
    }
}
