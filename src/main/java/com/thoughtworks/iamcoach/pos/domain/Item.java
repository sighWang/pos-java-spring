package com.thoughtworks.iamcoach.pos.domain;

import java.util.List;

public class Item {
    private int id;
    private String barcode;
    private String name;
    private String unit;
    private double price;
    private String category;
    private List<Promotion> promotionList;

    public Item() {
    }

    public Item(int id, String barcode, String name, String unit, double price, String category) {
        this.id = id;
        this.barcode = barcode;
        this.name = name;
        this.unit = unit;
        this.price = price;
        this.category = category;
    }

    public String getCategory() {
        return category;
    }

    public int getId() {
        return id;
    }

    public String getBarcode() {
        return barcode;
    }

    public String getName() {
        return name;
    }

    public String getUnit() {
        return unit;
    }

    public double getPrice() {
        return price;
    }

    public void setPromotionList(List<Promotion> promotionList) {
        this.promotionList = promotionList;
    }

    public List<Promotion> getPromotionList() {
        return promotionList;
    }
}
