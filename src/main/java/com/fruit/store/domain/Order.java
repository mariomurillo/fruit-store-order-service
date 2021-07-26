package com.fruit.store.domain;

import java.util.Collections;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "order_store")
public class Order {

  @Id
  @GeneratedValue
  private Long id;

  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  private final List<Item> items;

  private final Double total;

  public Order() {
    this.items = Collections.emptyList();
    this.total = 0.0;
  }

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
