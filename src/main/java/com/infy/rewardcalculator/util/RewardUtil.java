package com.infy.rewardcalculator.util;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.ZoneId;

public class RewardUtil {
    public static String getMonthFromMillis(long millis) {
        LocalDateTime localDateTime = Instant.ofEpochMilli(millis).atZone(ZoneId.systemDefault()).toLocalDateTime();
        Month month = localDateTime.getMonth();
        return month.toString();
    }

    public static int calculatePoints(double amount) {
        int points = 0;
        if (amount > 100) {
            points += (int) ((amount - 100) * 2);
            points += 50;
        }
        if (amount >= 50 && amount <= 100) {
            points += (int) (amount - 50);
        }
        return points;
    }
}
