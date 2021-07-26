package com.fruit.store.controller.model;

import java.io.Serializable;
import java.util.List;

public class OrderResponse implements Serializable {

  private final List<ItemResponse> items;

  private final Double total;

  public OrderResponse(List<ItemResponse> items, Double total) {
    this.items = items;
    this.total = total;
  }

  public List<ItemResponse> getItems() {
    return this.items;
  }

  public Double getTotal() {
    return this.total;
  }
}
