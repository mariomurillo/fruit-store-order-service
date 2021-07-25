package com.fruit.store.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import com.fruit.store.domain.Item;
import com.fruit.store.domain.ItemType;
import com.fruit.store.domain.Order;
import com.fruit.store.service.impl.DefaultOrderService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class OrderServiceTest {

  private OrderService service;

  @BeforeEach
  public void setUp() {
    Double appleCost = 60.0;
    Double orangeCost = 25.0;
    service = new DefaultOrderService(appleCost, orangeCost);
  }

  @Test
  public void buildTestSuccessful() {
    List<Item> items = new ArrayList<>();
    Item appleItem = new Item(ItemType.APPLE, 1);
    Item orangeItem = new Item(ItemType.ORANGE, 2);

    items.add(appleItem);
    items.add(orangeItem);

    Order order = service.buildOrder(items);

    assertNotNull(order);
    assertEquals(110.0, order.getTotal());
    assertEquals(60.0, order.getItems().get(0).getPrice());
  }
}
