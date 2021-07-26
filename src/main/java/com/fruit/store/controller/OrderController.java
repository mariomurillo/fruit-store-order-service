package com.fruit.store.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fruit.store.controller.model.ItemRequest;
import com.fruit.store.controller.model.ItemResponse;
import com.fruit.store.controller.model.OrderRequest;
import com.fruit.store.controller.model.OrderResponse;
import com.fruit.store.domain.Item;
import com.fruit.store.domain.ItemType;
import com.fruit.store.domain.Order;
import com.fruit.store.service.OrderService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/order")
public class OrderController {

  private final OrderService service;

  public OrderController(OrderService service) {
    this.service = service;
  }

  @PostMapping
  public ResponseEntity<OrderResponse> buildOrder(@RequestBody OrderRequest request) {
    List<Item> items = new ArrayList<>();
    for (ItemRequest itemRequest: request.getItems()) {
      items.add(new Item(ItemType.valueOf(itemRequest.getType().name()), itemRequest.getQuantity()));
    }
    Order order = service.buildOrder(items);
    List<ItemResponse> itemsResponse = new ArrayList<>();
    for (Item item : order.getItems()) {
      ItemResponse itemResponse = new ItemResponse(com.fruit.store.controller.model.ItemType.valueOf(
            item.getType().name()), item.getQuantity(), item.getPrice());
      itemsResponse.add(itemResponse);
    }
    return ResponseEntity.ok(new OrderResponse(itemsResponse, order.getTotal()));
  }

  @GetMapping(value = "/{id}")
  public ResponseEntity<OrderResponse> getOrder(@PathVariable("id") Long id) {
    Order order = service.getOrder(id);
    List<ItemResponse> items = order.getItems().stream().map(item -> new ItemResponse(
          com.fruit.store.controller.model.ItemType.valueOf(
            item.getType().name()),
          item.getQuantity(),
          item.getPrice()))
      .collect(Collectors.toList());
    return ResponseEntity.ok(new OrderResponse(items, order.getTotal()));
  }

  @GetMapping
  public ResponseEntity<List<OrderResponse>> getOrder() {
    List<Order> orders = service.getOrders();
    List<OrderResponse> response = orders.stream().map(order -> {
      List<ItemResponse> items = order.getItems().stream().map(item -> new ItemResponse(
            com.fruit.store.controller.model.ItemType.valueOf(
              item.getType().name()),
            item.getQuantity(),
            item.getPrice()))
        .collect(Collectors.toList());
      return new OrderResponse(items, order.getTotal());
    })
    .collect(Collectors.toList());
    return ResponseEntity.ok(response);
  }
}
