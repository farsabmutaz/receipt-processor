package com.receipt.util;

import com.receipt.model.Receipt;

public class PointCalculator {
    public static int calculatePoints(Receipt receipt) {
        int points = 0;
        points += receipt.getRetailer().replaceAll("[^a-zA-Z0-9]", "").length();
        if (receipt.getTotal().matches("\\d+\\.00")) {
            points += 50;
        }
        if (Double.parseDouble(receipt.getTotal()) % 0.25 == 0) {
            points += 25;
        }
        points += (receipt.getItems().size() / 2) * 5;

        for (Receipt.Item item : receipt.getItems()) {
            String trimmedDescription = item.getShortDescription().trim();
            if (trimmedDescription.length() % 3 == 0) {
                double price = Double.parseDouble(item.getPrice());
                points += Math.ceil(price * 0.2);
            }
        }

        int day = Integer.parseInt(receipt.getPurchaseDate().split("-")[2]);
        if (day % 2 != 0) {
            points += 6;
        }
        String[] timeParts = receipt.getPurchaseTime().split(":");
        int hour = Integer.parseInt(timeParts[0]);
        int minute = Integer.parseInt(timeParts[1]);
        if (hour == 14 || (hour == 15 && minute == 0)) {
            points += 10;
        }

        return points;
    }
}
