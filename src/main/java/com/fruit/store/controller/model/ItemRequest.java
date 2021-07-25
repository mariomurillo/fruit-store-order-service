package com.fruit.store.controller.model;

public class ItemRequest {

  private final ItemType type;

  private final Integer quantity;

  public ItemRequest(ItemType type, Integer quantity) {
    this.type = type;
    this.quantity = quantity;
  }

  public ItemType getType() {
    return this.type;
  }

  public Integer getQuantity() {
    return this.quantity;
  }
}
