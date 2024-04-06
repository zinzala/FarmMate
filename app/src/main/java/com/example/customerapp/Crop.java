package com.example.customerapp;

public class Crop {
    private String id;
    private String category;
    private String name;
    private String description;
    private String imageUrl;

    public Crop(String id ,String category, String name, String description, String imageUrl) {
        this.id = id;
        this.category=category;
        this.name = name;
        this.description = description;
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getId() {
        return id;
    }

    public String getCategory() {
        return category;
    }
}
