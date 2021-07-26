package com.fruit.store.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fruit.store.domain.Item;
import com.fruit.store.domain.ItemType;
import com.fruit.store.domain.Order;
import com.fruit.store.repository.OrderRepository;
import com.fruit.store.service.OfferService;
import com.fruit.store.service.OrderService;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class DefaultOrderService implements OrderService {

  private final Double appleCost;

  private final Double orangeCost;

  private final OfferService offerService;

  private final OrderRepository repository;

  public DefaultOrderService(@Value("${fruit.store.cost.apple}") Double appleCost,
      @Value("${fruit.store.cost.orange}") Double orangeCost,
      OfferService offerService,
      OrderRepository repository) {
    this.appleCost = appleCost;
    this.orangeCost = orangeCost;
    this.offerService = offerService;
    this.repository = repository;
  }

  public Order buildOrder(List<Item> items) throws IllegalArgumentException {

    Map<ItemType, Integer> itemsMap = new HashMap<>();

    for (Item item : items) {
      if (itemsMap.containsKey(item.getType())) {
        itemsMap.put(item.getType(), itemsMap.get(item.getType()) + item.getQuantity());
      } else {
        itemsMap.put(item.getType(), item.getQuantity());
      }
    }
    List<Item> orderItems = new ArrayList<>();
    for (Map.Entry<ItemType, Integer> entry: itemsMap.entrySet()) {
      Item item;
      switch (entry.getKey()) {
        case APPLE:
          item = new Item(ItemType.APPLE, entry.getValue(), entry.getValue() * appleCost);
          break;
        case ORANGE:
          item = new Item(ItemType.ORANGE, entry.getValue(), entry.getValue() * orangeCost);
          break;
        default:
          throw new IllegalArgumentException("Item type not supported.");
      }
      orderItems.add(item);
    }

    Collections.sort(orderItems);
    orderItems = offerService.apply(orderItems);
    Double total = 0.0;
    for (Item item : orderItems) {
      total += item.getPrice();
    }
    Order order = new Order(orderItems, total);
    repository.save(order);
    return order;
  }

  public Order getOrder(Long id) {
    return repository.findById(id).orElseThrow();
  }

  public List<Order> getOrders() {
    return repository.findAll();
  }
}
