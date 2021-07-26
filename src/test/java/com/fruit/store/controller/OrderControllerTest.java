package com.fruit.store.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import com.fruit.store.controller.model.ItemRequest;
import com.fruit.store.controller.model.ItemResponse;
import com.fruit.store.controller.model.ItemType;
import com.fruit.store.controller.model.OrderRequest;
import com.fruit.store.controller.model.OrderResponse;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class OrderControllerTest {

  @LocalServerPort
  private int port;

  @Autowired
  private TestRestTemplate restTemplate;

  @Test
  public void buildOrderSuccessfulTest() throws URISyntaxException {
    StringBuilder baseUrl = new StringBuilder("http://localhost:");
    baseUrl.append(this.port);
    baseUrl.append("/order");

    URI uri = new URI(baseUrl.toString());

    List<ItemRequest> items = new ArrayList<>();

    ItemRequest apple = new ItemRequest(ItemType.APPLE, 2);
    ItemRequest orange = new ItemRequest(ItemType.ORANGE, 3);

    items.add(apple);
    items.add(orange);

    OrderRequest OrderRequest = new OrderRequest(items);

    HttpEntity<OrderRequest> request = new HttpEntity<OrderRequest>(OrderRequest);

    ResponseEntity<OrderResponse> response = this.restTemplate.postForEntity(uri, request, OrderResponse.class);

    List<ItemResponse> itemsResponse = response.getBody().getItems();

    int appleIndex = 0;
    int orangeIndex = 0;

    for (int i = 0; i < itemsResponse.size(); i++) {
      appleIndex = ItemType.APPLE.equals(itemsResponse.get(i).getType()) ? i : appleIndex;
      orangeIndex = ItemType.ORANGE.equals(itemsResponse.get(i).getType()) ? i : orangeIndex;
    }

    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertNotNull(response.getBody());
    assertEquals(110.0, response.getBody().getTotal());
    assertEquals(60.0, itemsResponse.get(appleIndex).getPrice());
    assertEquals(2, itemsResponse.get(appleIndex).getQuantity());
    assertEquals(50.0, itemsResponse.get(orangeIndex).getPrice());
    assertEquals(3, itemsResponse.get(orangeIndex).getQuantity());
  }

  @Test
  public void getOrderByIdTest() {
    StringBuilder baseUrl = new StringBuilder("http://localhost:");
    baseUrl.append(this.port);
    baseUrl.append("/order");
    baseUrl.append("/{id}");

    ResponseEntity<OrderResponse> response = this.restTemplate.getForEntity(baseUrl.toString(), OrderResponse.class, 1l);

    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertNotNull(response);
    assertNotNull(response.getBody());
  }

  @Test
  public void getOrders() {
    StringBuilder baseUrl = new StringBuilder("http://localhost:");
    baseUrl.append(this.port);
    baseUrl.append("/order");

    var response = this.restTemplate.getForEntity(baseUrl.toString(), List.class);

    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertNotNull(response);
    assertNotNull(response.getBody());
  }
}
