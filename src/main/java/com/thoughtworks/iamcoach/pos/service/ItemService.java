package com.thoughtworks.iamcoach.pos.service;

import com.thoughtworks.iamcoach.pos.dao.ItemDao;
import com.thoughtworks.iamcoach.pos.dao.PromotionDao;
import com.thoughtworks.iamcoach.pos.domain.Item;

public class ItemService {
    private ItemDao itemDao;
    private PromotionDao promotionDao;

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
        item.setPromotionList(promotionDao.getItemPromotionList(item.getId()));
        return item;
    }

}
