package com.thoughtworks.iamcoach.pos.service;

import com.thoughtworks.iamcoach.pos.domain.BoughtItem;
import com.thoughtworks.iamcoach.pos.domain.Item;
import com.thoughtworks.iamcoach.pos.domain.Promotion;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.fest.assertions.api.Assertions.assertThat;

public class BoughtItemServiceTest {
    private BoughtItemService boughtItemService;

    @Before
    public void init() {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContextTest.xml");
        boughtItemService = (BoughtItemService) context.getBean("boughtItemService");
    }

    @Test
    public void barcodes_to_boughtItems_test() {
        List<String> cartBarcodes = new ArrayList<String>();
        cartBarcodes.add("ITEM000001");
        cartBarcodes.add("ITEM000001");
        cartBarcodes.add("ITEM000001");
        cartBarcodes.add("ITEM000001");
        cartBarcodes.add("ITEM000006-2.00");
        cartBarcodes.add("ITEM000006-2.00");


        List<BoughtItem> boughtItemList = boughtItemService.barcodesToBoughtItems(cartBarcodes);
        BoughtItem boughtItem1 = boughtItemList.get(0);
        BoughtItem boughtItem2 = boughtItemList.get(1);

        assertThat(boughtItem1.getNumber()).isEqualTo(4.00);
        assertThat(boughtItem1.getBarcode()).isEqualTo("ITEM000001");

        assertThat(boughtItem2.getNumber()).isEqualTo(4.00);
        assertThat(boughtItem2.getBarcode()).isEqualTo("ITEM000006");
    }

    @Test
    public void get_category_set_of_boughtItems_test() {
        Item item1 = new Item(1, "ITEM000002", "apple", "kg", 10.00, "fruit");
        Item item2 = new Item(2, "ITEM000003", "banana", "kg", 10.00, "fruit");
        List<BoughtItem> boughtItemList = new ArrayList<BoughtItem>();
        Promotion promotion = new Promotion(1, "buy_two_get_one_free", 1);

        BoughtItem boughtItem1 = new BoughtItem(item1, 3, promotion, 20.0);
        BoughtItem boughtItem2 = new BoughtItem(item2, 3, promotion, 20.0);
        boughtItemList.add(boughtItem1);
        boughtItemList.add(boughtItem2);

        Set<String> categorySet = boughtItemService.getCategorySetOfBoughtItems(boughtItemList);
        assertThat(categorySet).hasSize(1);
    }

}
