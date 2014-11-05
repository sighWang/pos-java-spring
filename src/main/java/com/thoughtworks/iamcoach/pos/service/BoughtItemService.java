package com.thoughtworks.iamcoach.pos.service;

import com.thoughtworks.iamcoach.pos.Barcode;
import com.thoughtworks.iamcoach.pos.domain.BoughtItem;
import com.thoughtworks.iamcoach.pos.domain.Item;
import com.thoughtworks.iamcoach.pos.domain.Promotion;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BoughtItemService {
    private Barcode barcode = new Barcode();

    public List<BoughtItem> barcodesToBoughtItems(List<String> cartBarcodes) {
        ItemService itemService = new ItemService();
        List<BoughtItem> boughtItemList = new ArrayList<BoughtItem>();
        List<String> uniqueBarcodes = barcode.uniqueBarcode(cartBarcodes);

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
