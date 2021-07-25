package com.fruit.store.domain;

import java.util.List;

public class Order {

  private final List<Item> items;

  private final Double total;

  public Order(List<Item> items, Double total) {
    this.items = items;
    this.total = total;
  }

  public List<Item> getItems() {
    return this.items;
  }

  public Double getTotal() {
    return this.total;
  }
}
