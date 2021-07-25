package com.fruit.store.service;

import java.util.List;

import com.fruit.store.domain.Item;

public interface OfferService {

  public List<Item> apply(List<Item> items);
}
