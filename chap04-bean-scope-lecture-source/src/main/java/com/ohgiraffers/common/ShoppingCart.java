package com.ohgiraffers.common;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {

    private final List<Product> items;

    public ShoppingCart() {
        items = new ArrayList<>();
    }

    /* 설명. 쇼핑카트에 물품을 담거나 꺼내는 기능 */
    public void addItem(Product p) {
        items.add(p);
    }

    public List<Product> getItems() {
        return items;
    }

    @Override
    public String toString() {
        return "ShoppingCart{" +
                "items=" + items +
                '}';
    }
}
