package com.fruit.store.service;

import java.util.List;

import com.fruit.store.domain.Item;
import com.fruit.store.domain.Order;

public interface OrderService {

  public Order buildOrder(List<Item> items) throws IllegalArgumentException;

  public Order getOrder(Long id);

  public List<Order> getOrders();
}
