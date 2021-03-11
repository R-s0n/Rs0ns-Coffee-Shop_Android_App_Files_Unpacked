package com.rson.rsoncoffeeshop;

import java.util.List;

public class Cart {
    private Long id;
    private User customer;
    private List<Coffee> items;

    public Cart() {}

    public Long getId() {
        return id;
    }

    public void addCoffee(Coffee coffee){
        items.add(coffee);
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getCustomer() {
        return customer;
    }

    public void setCustomer(User customer) {
        this.customer = customer;
    }

    public List<Coffee> getItems() {
        return items;
    }

    public void setItems(List<Coffee> items) {
        this.items = items;
    }
}
