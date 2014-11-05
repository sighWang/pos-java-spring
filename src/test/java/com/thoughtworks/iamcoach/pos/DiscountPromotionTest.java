package com.thoughtworks.iamcoach.pos;

import com.thoughtworks.iamcoach.pos.domain.promotion.DiscountPromotion;
import com.thoughtworks.iamcoach.pos.domain.Item;
import org.junit.Test;

import static org.fest.assertions.api.Assertions.assertThat;

public class DiscountPromotionTest {
    DiscountPromotion discountPromotion = new DiscountPromotion(2, "second_half_price", 50, 2);

    @Test
    public void calculate_test() {
        Item item = new Item(1, "ITEM000002", "apple", "kg", 10.00, "fruit");
        assertThat(discountPromotion.calculate(item, 5.0)).isEqualTo(25.0);
    }
}
