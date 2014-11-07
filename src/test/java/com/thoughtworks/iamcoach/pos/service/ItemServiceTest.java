package com.thoughtworks.iamcoach.pos.service;

import com.thoughtworks.iamcoach.pos.dao.ItemDao;
import com.thoughtworks.iamcoach.pos.dao.PromotionDao;
import com.thoughtworks.iamcoach.pos.domain.Item;
import com.thoughtworks.iamcoach.pos.domain.Promotion;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;
import java.util.List;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ItemServiceTest {
    private ItemService itemService;

    @Before
    public void init() {
        itemService = new ItemService();

        Item item = new Item(1, "ITEM000001", "apple", "kg", 10.00, "fruit");
        ItemDao itemDao = mock(ItemDao.class);
        when(itemDao.getItemById(1)).thenReturn(item);
        when(itemDao.getItemByBarcode("ITEM000001")).thenReturn(item);
        itemService.setItemDao(itemDao);

        Promotion promotion = new Promotion(1,"discount",50,1);
        List<Promotion> promotions = new ArrayList<Promotion>();
        promotions.add(promotion);
        PromotionDao promotionDao = mock(PromotionDao.class);
        when(promotionDao.getItemPromotionList(1)).thenReturn(promotions);
        itemService.setPromotionDao(promotionDao);
    }

    @Test
    public void find_item_by_id_test() {
        int id = 1;
        assertThat(itemService.findItemById(id).getBarcode()).isEqualTo("ITEM000001");
    }

    @Test
    public void get_promotions_by_item_id_test() {
        int id = 1;
        assertThat(itemService.findItemById(id).getPromotionList()).hasSize(1);
    }

    @Test
    public void find_item_by_barcode_test() {
        String barcode = "ITEM000001";
        assertThat(itemService.findItemByBarcode(barcode).getName()).isEqualTo("apple");
        assertThat(itemService.findItemByBarcode(barcode).getPromotionList()).hasSize(1);
    }
}

