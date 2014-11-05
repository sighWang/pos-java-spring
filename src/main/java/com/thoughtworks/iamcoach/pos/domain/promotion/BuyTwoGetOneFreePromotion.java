package com.thoughtworks.iamcoach.pos.domain.promotion;

import com.thoughtworks.iamcoach.pos.domain.Item;
import com.thoughtworks.iamcoach.pos.domain.Promotion;

public class BuyTwoGetOneFreePromotion extends Promotion {

    public BuyTwoGetOneFreePromotion(int id, String type, int level) {
        super(id, type, level);
    }

    @Override
    public double calculate(Item item, double number) {

        double payNumber = number - (int) (number / 3);

        return item.getPrice() * payNumber;
    }
}
