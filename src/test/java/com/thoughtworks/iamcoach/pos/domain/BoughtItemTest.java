package com.thoughtworks.iamcoach.pos.domain;

import org.junit.Test;

public class BoughtItemTest {
    Item item = new Item(1, "ITEM000002", "apple", "kg", 10.00, "水果");
    Promotion promotion = new Promotion(1, "buy_two_get_one_free", 1);
    BoughtItem boughtItem = new BoughtItem(item, 5, promotion, 40.0);

    //TODO:test need to be written
    @Test
    public void compareTo_test() {

    }
}
