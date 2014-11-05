package com.thoughtworks.iamcoach.pos;

import com.thoughtworks.iamcoach.pos.domain.promotion.BuyTwoGetOneFreePromotion;
import com.thoughtworks.iamcoach.pos.domain.promotion.DiscountPromotion;
import com.thoughtworks.iamcoach.pos.domain.promotion.SecondHalfPricePromotion;
import org.junit.Test;

import static org.fest.assertions.api.Assertions.assertThat;

public class PromotionFactoryTest {

    @Test
    public void create_promotion_test() {
        String type1 = "buy_two_get_one_free";
        String type2 = "second_half_price";
        String type3 = "discount";

        assertThat(PromotionFactory.createPromotion(1, type1, 0, 1)).isInstanceOf(BuyTwoGetOneFreePromotion.class);
        assertThat(PromotionFactory.createPromotion(1, type2, 0, 1)).isInstanceOf(SecondHalfPricePromotion.class);
        assertThat(PromotionFactory.createPromotion(1, type3, 75, 1)).isInstanceOf(DiscountPromotion.class);
    }
}
