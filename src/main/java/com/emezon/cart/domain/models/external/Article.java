package com.emezon.cart.domain.models.external;

import java.time.LocalDateTime;
import java.util.List;

public class Article {

    private String id;
    private String name;
    private String description;
    private double price;
    private int stock;
    private LocalDateTime restockDate;
    private List<Category> categories;

    public Article() {}

    public Article(String id, String name, String description, double price, int stock, List<Category> categories) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.stock = stock;
        this.categories = categories;
    }

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
