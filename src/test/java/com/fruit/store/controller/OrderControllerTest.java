package com.fruit.store.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import com.fruit.store.controller.model.ItemRequest;
import com.fruit.store.controller.model.ItemType;
import com.fruit.store.controller.model.OrderRequest;
import com.fruit.store.controller.model.OrderResponse;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class OrderControllerTest {

  @LocalServerPort
  private int port;

  @Autowired
  private TestRestTemplate restTemplate;

  @Test
  public void buildOrderSuccessfulTest() throws URISyntaxException{
    StringBuilder baseUrl = new StringBuilder("http://localhost:");
    baseUrl.append(this.port);
    baseUrl.append("/order");

    URI uri = new URI(baseUrl.toString());

    List<ItemRequest> items = new ArrayList<>();

    ItemRequest apple = new ItemRequest(ItemType.APPLE, 1);
    ItemRequest orange = new ItemRequest(ItemType.ORANGE, 1);

    items.add(apple);
    items.add(orange);

    OrderRequest OrderRequest = new OrderRequest(items);

    HttpEntity<OrderRequest> request = new HttpEntity<OrderRequest>(OrderRequest);

    ResponseEntity<OrderResponse> response = this.restTemplate.postForEntity(uri, request, OrderResponse.class);

    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertNotNull(response.getBody());
    assertEquals(85.0, response.getBody().getTotal());
  }
}
