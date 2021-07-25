package com.fruit.store.domain;

public class Item {

  private final ItemType type;

  private final Integer quantity;

  private final Double price;

  public Item(ItemType type, Integer quantity) {
    this.type = type;
    this.quantity = quantity;
    this.price = null;
  }

  public Item(ItemType type, Integer quantity, Double price) {
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
