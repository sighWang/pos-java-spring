package com.thoughtworks.iamcoach.pos.domain.promotion;

import com.thoughtworks.iamcoach.pos.domain.Item;
import com.thoughtworks.iamcoach.pos.domain.Promotion;

public class SecondHalfPricePromotion extends Promotion {

    public SecondHalfPricePromotion(int id, String type, int level) {
        super(id, type, level);
    }

    @Override
    public double calculate(Item item, double number) {

        double total = item.getPrice() * number;
        double discountTotal = (0.5 * item.getPrice()) * (int) (number / 2);

        return total - discountTotal;
    }
}
