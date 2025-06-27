package com.example.shopping.service;

import com.example.shopping.model.Item;
import jakarta.inject.Singleton;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
// import java.util.concurrent.atomic.AtomicLong;

@Singleton
public class ItemService {
    private final Map<Long, Item> items = new ConcurrentHashMap<>();
    // private final AtomicLong counter = new AtomicLong(1);

    public List<Item> getAllItems() {
        return new ArrayList<>(items.values());
    }

    public Optional<Item> getItemById(Long id) {
        return Optional.ofNullable(items.get(id));
    }

    public Item createOrIncrease(String name, int quantity) {
        validateInput(name, quantity);

        Optional<Item> existing = items.values().stream()
                .filter(i -> i.getName().equalsIgnoreCase(name))
                .findFirst();
        
        if (existing.isPresent()) {
            Item item = existing.get();
            item.setQuantity(item.getQuantity() + quantity);
            return item;
            
        }
        Item newItem = new Item(name, quantity);
        items.put(newItem.getId(), newItem);
        return newItem;
    }

    public Optional<Item> updateItem(Long id, String name, int quantity) {
        validateInput(name, quantity);
        return Optional.ofNullable(items.get(id))
                .map(item -> {
                    item.setName(name);
                    item.setQuantity(quantity);
                    return item;
                });
    }

    public boolean delete(Long id) {
        return items.remove(id) != null;
    }

    private void validateInput(String name, int quantity) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Item-Name darf nicht leer sein.");
        }
        if (quantity <= 0) {
            throw new IllegalArgumentException("Menge muss größer als 0 sein.");
        }
    }
}
