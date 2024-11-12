package com.emezon.cart.domain.models.external;

import java.time.LocalDateTime;
import java.util.List;

public class Article {

    private String id;
    private String name;
    private String description;
    private double price;
    private int stock;
    private Brand brand;
    private LocalDateTime restockDate;
    private List<Category> categories;

    public Article() {}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public LocalDateTime getRestockDate() {
        return restockDate;
    }

    public void setRestockDate(LocalDateTime restockDate) {
        this.restockDate = restockDate;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }
}
