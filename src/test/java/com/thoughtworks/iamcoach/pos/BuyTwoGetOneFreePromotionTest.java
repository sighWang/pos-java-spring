package com.thoughtworks.iamcoach.pos;

import com.thoughtworks.iamcoach.pos.domain.promotion.BuyTwoGetOneFreePromotion;
import com.thoughtworks.iamcoach.pos.domain.Item;
import org.junit.Test;

import static org.fest.assertions.api.Assertions.assertThat;

public class BuyTwoGetOneFreePromotionTest {
    BuyTwoGetOneFreePromotion buyTwoGetOneFree = new BuyTwoGetOneFreePromotion(1, "buy_two_get_one_free", 1);

    @Test
    public void calculate_test() {
        Item item = new Item(1, "ITEM000002", "apple", "kg", 10.00, "fruit");

        assertThat(buyTwoGetOneFree.calculate(item, 5.0)).isEqualTo(40.0);
        assertThat(buyTwoGetOneFree.calculate(item, 3.0)).isEqualTo(20.0);
        assertThat(buyTwoGetOneFree.calculate(item, 1.0)).isEqualTo(10.0);
        assertThat(buyTwoGetOneFree.calculate(item, 0)).isEqualTo(0.0);
    }
}
