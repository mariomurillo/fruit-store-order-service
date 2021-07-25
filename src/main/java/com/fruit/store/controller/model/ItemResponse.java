package com.fruit.store.controller.model;

public class ItemResponse {

  private final ItemType type;

  private final Integer quantity;

  private final Double price;

  public ItemResponse(ItemType type, Integer quantity, Double price) {
    this.type = type;
    this.quantity = quantity;
    this.price = price;
  }

  public ItemType getType() {
    return this.type;
  }

  public Integer getQuantity() {
    return this.quantity;
  }

  public Double getPrice() {
    return this.price;
  }
}
