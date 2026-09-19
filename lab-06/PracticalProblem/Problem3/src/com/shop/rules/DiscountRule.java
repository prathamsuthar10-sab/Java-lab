package com.shop.rules;

@FunctionalInterface
public interface DiscountRule {
    double apply(double price);
}