package com.shop.service;

import java.util.ArrayList;
import java.util.List;
import com.shop.rules.DiscountRule;

public class DiscountEngine {
    public List<Double> applyDiscount(List<Double> prices, DiscountRule rule) {
        List<Double> finalPrices = new ArrayList<>();

        for (double price : prices) {
            finalPrices.add(rule.apply(price));
        }

        return finalPrices;
    }
}