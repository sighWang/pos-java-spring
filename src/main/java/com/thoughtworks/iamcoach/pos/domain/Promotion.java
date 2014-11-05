package com.thoughtworks.iamcoach.pos.domain;

public class Promotion {
    private int id;
    private String type;
    private int discount;
    private int level;

    public Promotion() {
    }

    public Promotion(int id, String type, int level) {
        this.id = id;
        this.type = type;
        this.level = level;
    }

    public Promotion(int id, String type, int discount, int level) {
        this.id = id;
        this.type = type;
        this.discount = discount;
        this.level = level;
    }

    public int getDiscount() {
        return discount;
    }

    public int getLevel() {
        return level;
    }

    public double calculate(Item item, double number) {
        return item.getPrice() * number;
    }
}
