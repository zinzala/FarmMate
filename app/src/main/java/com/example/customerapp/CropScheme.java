package com.example.customerapp;

public class CropScheme {
    private String id;
    private String category;
    private String name;

    private String imageUrl;


    public CropScheme(String id ,String category, String name, String imageUrl) {
        this.id = id;
        this.category=category;
        this.name = name;
        this.imageUrl=imageUrl;

    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public String getCategory() {
        return category;
    }
    public String getImageUrl() {
        return imageUrl;
    }
}
