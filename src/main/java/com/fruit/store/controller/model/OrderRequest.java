package com.fruit.store.controller.model;

import java.util.Collections;
import java.util.List;

public class OrderRequest {

  private final List<ItemRequest> items;

  public OrderRequest() {
    this.items = Collections.emptyList();
  }

  public OrderRequest(List<ItemRequest> items) {
    this.items = items;
  }

  public List<ItemRequest> getItems() {
    return this.items;
  }
}
