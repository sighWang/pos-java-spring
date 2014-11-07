package com.thoughtworks.iamcoach.pos.service;

import com.thoughtworks.iamcoach.pos.dao.ItemDao;
import com.thoughtworks.iamcoach.pos.dao.PromotionDao;
import com.thoughtworks.iamcoach.pos.domain.Item;
import com.thoughtworks.iamcoach.pos.domain.Promotion;

import java.util.List;

public class ItemService {
    private ItemDao itemDao;
    private PromotionDao promotionDao;
    public ItemService() {}

    public ItemService(ItemDao itemDao, PromotionDao promotionDao) {
        this.itemDao = itemDao;
        this.promotionDao = promotionDao;
    }

    public void setItemDao(ItemDao itemDao) {
        this.itemDao = itemDao;
    }

    public void setPromotionDao(PromotionDao promotionDao) {
        this.promotionDao = promotionDao;
    }

    public Item findItemById(int id) {
        Item item = itemDao.getItemById(id);
        item.setPromotionList(promotionDao.getItemPromotionList(id));
        return item;
    }

    public Item findItemByBarcode(String barcode) {
        Item item = itemDao.getItemByBarcode(barcode);
        List<Promotion> promotions = promotionDao.getItemPromotionList(item.getId());
        if (promotions.size() == 0) {
            promotions.add(new Promotion());
        }
        item.setPromotionList(promotions);
        return item;
    }

}
