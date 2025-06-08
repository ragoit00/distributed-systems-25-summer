package com.example.shopping.controller;

import com.example.shopping.model.Item;
import com.example.shopping.service.ItemService;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.*;

import jakarta.inject.Inject;
import java.util.List;
import java.util.Optional;

@Controller("/items")
public class ItemController {

    private final ItemService service;

    @Inject
    public ItemController(ItemService service) {
        this.service = service;
    }

    @Get("/")
    public List<Item> getItems() {
        return service.findAll();
    }

    @Get("/{id}")
    public HttpResponse<Item> getItemById(@PathVariable Long id) {
        return service.findById(id)
                .map(HttpResponse::ok)
                .orElse(HttpResponse.notFound());
    }

    @Post("/")
    public HttpResponse<Item> createOrIncrease(@Body ItemRequest request) {
        try {
            Item item = service.createOrIncrease(request.name(), request.quantity());
            return HttpResponse.created(item);
        } catch (IllegalArgumentException e) {
            return HttpResponse.badRequest();
        }
    }

    @Put("/{id}")
    public HttpResponse<Item> updateItem(Long id, @Body ItemRequest request) {
        try {
            Optional<Item> updated = service.update(id, request.name(), request.quantity());
            return updated.map(HttpResponse::ok)
                    .orElse(HttpResponse.notFound());
        } catch (IllegalArgumentException e) {
            return HttpResponse.badRequest();
        }
    }

    @Delete("/{id}")
    public HttpResponse<?> deleteItem(Long id) {
        return service.delete(id)
                ? HttpResponse.noContent()
                : HttpResponse.notFound();
    }

    public static record ItemRequest(String name, int quantity) {
    }
}
