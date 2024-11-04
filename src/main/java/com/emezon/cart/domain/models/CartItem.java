package com.emezon.cart.domain.models;

import com.emezon.cart.domain.models.external.Article;

import java.time.LocalDateTime;

public class CartItem {

    private String id;
    private Cart cart;
    private Article article;
    private int quantity;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public CartItem() {}

    public CartItem(String id, Cart cart, Article article, int quantity) {
        this.id = id;
        this.cart = cart;
        this.article = article;
        this.quantity = quantity;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
