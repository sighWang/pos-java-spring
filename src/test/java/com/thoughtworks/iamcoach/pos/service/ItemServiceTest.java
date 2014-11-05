package com.thoughtworks.iamcoach.pos.service;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.fest.assertions.api.Assertions.assertThat;

public class ItemServiceTest {
    private ItemService itemService;

    @Before
    public void init() {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContextTest.xml");
        itemService = (ItemService)context.getBean("itemService");
    }

    @Test
    public void find_item_by_id_test() {
        int id = 1;
        assertThat(itemService.findItemById(id).getBarcode()).isEqualTo("ITEM000001");
    }

    @Test
    public void get_promotions_by_item_id_test() {
        int id = 1;
        assertThat(itemService.findItemById(id).getPromotionList()).hasSize(2);
    }

    @Test
    public void find_item_by_barcode_test() {
        String barcode = "ITEM000001";
        assertThat(itemService.findItemByBarcode(barcode).getName()).isEqualTo("雪碧");
        assertThat(itemService.findItemByBarcode(barcode).getPromotionList()).hasSize(2);
    }
}

