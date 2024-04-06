package com.example.customerapp;

public class CropPrices {
    private String id;
    private String category;
    private String name;
    private String id1;
    private String id2;


    public CropPrices(String id ,String category, String name, String id1, String id2) {
        this.id = id;
        this.category=category;
        this.name = name;
        this.id1 = id1;
        this.id2 = id2;

    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return id1;
    }

    public String getDescription2() {
        return id2;
    }


    public String getId() {
        return id;
    }

    public String getCategory() {
        return category;
    }
}
