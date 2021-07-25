package com.fruit.store.domain;

import java.util.Objects;

public class Item implements Comparable<Item> {

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

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null ||getClass() != o.getClass()) return false;
    Item that = (Item) o;
    return Objects.equals(this.getType(), that.getType())
      && Objects.equals(this.getQuantity(), that.getQuantity())
      && Objects.equals(this.getPrice(), that.getPrice());
  }

  @Override
  public int compareTo(Item that) {
    return getType().compareTo(that.getType());
  }
}
