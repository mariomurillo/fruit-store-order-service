package com.fruit.store.controller.model;

public enum ItemType {
  APPLE("Apple"),
  ORANGE("Orange");

  private final String value;

  private ItemType(String value) {
    this.value = value;
  }

  public String getValue() {
    return this.value;
  }
}
