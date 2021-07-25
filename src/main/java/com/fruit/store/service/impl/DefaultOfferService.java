package com.fruit.store.service.impl;

import java.util.Arrays;
import java.util.List;

import com.fruit.store.domain.Item;
import com.fruit.store.domain.ItemType;
import com.fruit.store.service.OfferService;

import org.springframework.stereotype.Service;

@Service
public class DefaultOfferService implements OfferService {

  public List<Item> apply(List<Item> items) {

    Item appleItem = applyAppleOffer(items);
    Item orangeItem = applyOrangeOffer(items);

    return Arrays.asList(appleItem, orangeItem);
  }

  private Item applyAppleOffer(List<Item> items) {
    Item appleItem = null;
    for (Item item : items) {
      if (ItemType.APPLE.equals(item.getType())) {
        Double price = item.getQuantity() % 2 == 0 ? item.getPrice()/2.0 : item.getPrice();
        appleItem = new Item(item.getType(), item.getQuantity(), price);
      }
    }
    return appleItem;
  }

  private Item applyOrangeOffer(List<Item> items) {
    Item orangeItem = null;
    for (Item item : items) {
      if (ItemType.ORANGE.equals(item.getType())) {
        Double price = item.getQuantity() % 3 == 0 ? item.getPrice()/(3.0/2.0) : item.getPrice();
        orangeItem = new Item(ItemType.ORANGE, item.getQuantity(), price);
      }
    }
    return orangeItem;
  }
}
