package com.thoughtworks.iamcoach.pos.domain;

public class BoughtItem implements Comparable {
    private int id;
    private String barcode;
    private String name;
    private String unit;
    private double price;
    private String category;
    private double number;
    private Promotion promotion;
    private double subtotal;

    public BoughtItem(Item item, double number, Promotion promotion, double subtotal) {
        this.id = item.getId();
        this.barcode = item.getBarcode();
        this.name = item.getName();
        this.unit = item.getUnit();
        this.price = item.getPrice();
        this.category = item.getCategory();
        this.number = number;
        this.promotion = promotion;
        this.subtotal = subtotal;
    }

    public double getSubtotal() {
        return subtotal;
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

    public String getCategory() {
        return category;
    }

    public double getNumber() {
        return number;
    }

    public Promotion getPromotion() {
        return promotion;
    }

    @Override
    public int compareTo(Object o) {
        double result = this.subtotal - ((BoughtItem) o).getSubtotal();

        if (result == 0) {
            result = this.promotion.getLevel() - ((BoughtItem) o).getPromotion().getLevel();
        }

        return (int) result;
    }

}
