package com.fruit.store.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import com.fruit.store.domain.Item;
import com.fruit.store.domain.ItemType;
import com.fruit.store.domain.Order;
import com.fruit.store.service.impl.DefaultOrderService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class OrderServiceTest {

  private OrderService service;

  private OfferService offerService;

  @BeforeEach
  public void setUp() {
    Double appleCost = 60.0;
    Double orangeCost = 25.0;

    offerService = mock(OfferService.class);

    service = new DefaultOrderService(appleCost, orangeCost, offerService);
  }

  @Test
  public void buildTestSuccessful() {
    List<Item> items = Arrays.asList(new Item(ItemType.APPLE, 2), new Item(ItemType.ORANGE, 3));
    List<Item> itemsResponse = Arrays.asList(new Item(ItemType.APPLE, 2, 60.0), new Item(ItemType.ORANGE, 3, 50.0));
    List<Item> itemsWithoutOffer = Arrays.asList(new Item(ItemType.APPLE, 2, 120.0), new Item(ItemType.ORANGE, 3, 75.0));

    when(offerService.apply(itemsWithoutOffer)).thenReturn(itemsResponse);

    Order order = service.buildOrder(items);

    List<Item> orderItems = order.getItems();

    int appleIndex = 0;
    int orangeIndex = 0;
    
    for (int i = 0; i < orderItems.size(); i++) {
      appleIndex = ItemType.APPLE.equals(orderItems.get(i).getType()) ? i : appleIndex;
      orangeIndex = ItemType.ORANGE.equals(orderItems.get(i).getType()) ? i : orangeIndex;
    }

    assertNotNull(order);
    assertEquals(110.0, order.getTotal());
    assertEquals(60.0, order.getItems().get(appleIndex).getPrice());
    assertEquals(50.0, order.getItems().get(orangeIndex).getPrice());

    verify(offerService, times(1)).apply(itemsWithoutOffer);
  }
}
