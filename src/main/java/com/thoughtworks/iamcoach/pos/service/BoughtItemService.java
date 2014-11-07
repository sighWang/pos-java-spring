package com.thoughtworks.iamcoach.pos.service;

import com.thoughtworks.iamcoach.pos.Barcode;
import com.thoughtworks.iamcoach.pos.domain.BoughtItem;
import com.thoughtworks.iamcoach.pos.domain.Item;
import com.thoughtworks.iamcoach.pos.domain.Promotion;

import java.util.*;

public class BoughtItemService {

    private Barcode barcode;
    private ItemService itemService;
    public BoughtItemService() {}

    public BoughtItemService(Barcode barcode, ItemService itemService) {
        this.barcode = barcode;
        this.itemService = itemService;
    }

    public void setBarcode(Barcode barcode) {
        this.barcode = barcode;
    }

    public void setItemService(ItemService itemService) {
        this.itemService = itemService;
    }

    public List<BoughtItem> barcodesToBoughtItems(List<String> cartBarcodes) {
        List<BoughtItem> boughtItemList = new ArrayList<BoughtItem>();
        Set<String> uniqueBarcodes = new HashSet<String>();
        uniqueBarcodes.addAll(cartBarcodes);

        for (String uniqueBarcode : uniqueBarcodes) {
            int times = barcode.getBarcodeTimes(cartBarcodes, uniqueBarcode);
            double number = barcode.getBarcodeNumber(uniqueBarcode, times);

            String itemBarcode = barcode.getItemBarcode(uniqueBarcode);
            Item item = itemService.findItemByBarcode(itemBarcode);

            BoughtItem boughtItem = determineBoughtItem(item, number);
            boughtItemList.add(boughtItem);
        }
        return boughtItemList;
    }

    public Set<String> getCategorySetOfBoughtItems(List<BoughtItem> boughtItemList) {
        Set<String> categorySet = new HashSet<String>();

        for(BoughtItem boughtItem : boughtItemList) {
            String category = boughtItem.getCategory();
            categorySet.add(category);
        }
        return categorySet;
    }

    private BoughtItem determineBoughtItem(Item item, double number) {
        List<Promotion> promotionList = item.getPromotionList();
        List<BoughtItem> boughtItems = new ArrayList<BoughtItem>();

        for (Promotion promotion : promotionList) {
            double subtotal = promotion.calculate(item, number);
            BoughtItem boughtItem = new BoughtItem(item, number, promotion, subtotal);
            boughtItems.add(boughtItem);
        }
        return findMinSubtotal(boughtItems);
    }

    private BoughtItem findMinSubtotal(List<BoughtItem> boughtItems) {
        Collections.sort(boughtItems);
        return boughtItems.get(0);
    }
}
