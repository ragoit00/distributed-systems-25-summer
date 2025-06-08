package com.example.shopping.model;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "Item", description = "Represents a shopping list item")
public class Item {
    private Long id;
    private String name;
    private int quantity;

    public Item() {}

    public Item(Long id, String name, int quantity) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
    }
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }


    // Getters and setters
}
