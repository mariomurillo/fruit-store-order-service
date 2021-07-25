package com.fruit.store.controller;

import java.util.ArrayList;
import java.util.List;

import com.fruit.store.controller.model.ItemRequest;
import com.fruit.store.controller.model.ItemResponse;
import com.fruit.store.controller.model.OrderRequest;
import com.fruit.store.controller.model.OrderResponse;
import com.fruit.store.domain.Item;
import com.fruit.store.domain.ItemType;
import com.fruit.store.domain.Order;
import com.fruit.store.service.OrderService;

import org.springframework.http.ResponseEntity;
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
    List<ItemResponse> itemsResponse = new ArrayList();
    for (Item item : order.getItems()) {
      ItemResponse itemResponse = new ItemResponse(com.fruit.store.controller.model.ItemType.valueOf(
            item.getType().name()), item.getQuantity(), 0.0);
      itemsResponse.add(itemResponse);
    }
    return ResponseEntity.ok(new OrderResponse(itemsResponse, order.getTotal()));
  }
}
