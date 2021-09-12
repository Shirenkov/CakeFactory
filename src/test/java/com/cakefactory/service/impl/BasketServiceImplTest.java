package com.cakefactory.service.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.cakefactory.domain.Basket;
import com.cakefactory.domain.BasketItem;
import com.cakefactory.domain.Pastry;
import com.cakefactory.service.BasketService;
import java.math.BigDecimal;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
class BasketServiceImplTest {

  @MockBean
  private Basket basket;
  @Autowired
  private BasketService basketService;

  @Test
  void addToBasket() {
    String id = "abc";
    Pastry pastry = Pastry.builder()
        .id(id)
        .title("Chocolate Croissant")
        .price(new BigDecimal("0.95"))
        .build();
    BasketItem basketItem = new BasketItem(pastry, 1);
    when(basket.getItems()).thenReturn(List.of(basketItem));

    basketService.addToBasket(id);

    assertThat(basketItem.getQuantity()).isEqualTo(2);
    assertThat(basketItem.getTotal()).isEqualTo(new BigDecimal("1.90"));
  }

  @Test
  void removeLastItemFromBasket() {
    String id = "abc";
    Pastry pastry = Pastry.builder()
        .id(id)
        .title("Chocolate Croissant")
        .price(new BigDecimal("0.95"))
        .build();
    BasketItem basketItem = new BasketItem(pastry, 1);
    when(basket.getItems()).thenReturn(List.of(basketItem));

    basketService.removeFromBasket(id);

    verify(basket).removeItem(basketItem);
  }

  @Test
  void removeItemFromBasket() {
    String id = "abc";
    Pastry pastry = Pastry.builder()
        .id(id)
        .title("Chocolate Croissant")
        .price(new BigDecimal("0.95"))
        .build();
    BasketItem basketItem = new BasketItem(pastry, 2);
    when(basket.getItems()).thenReturn(List.of(basketItem));

    basketService.removeFromBasket(id);

    assertThat(basketItem.getQuantity()).isEqualTo(1);
    assertThat(basketItem.getTotal()).isEqualTo(new BigDecimal("0.95"));
    verify(basket, never()).removeItem(basketItem);
  }

  @Test
  void clearBasket() {
    basketService.clearBasket();

    verify(basket).clearItems();
  }

  @Test
  void viewBasket() {
    assertThat(basketService.viewBasket()).isEqualTo(basket);
  }

  @Test
  void getNumberOfItems() {
    when(basket.getTotalNumber()).thenReturn(3);

    assertThat(basketService.getNumberOfItems()).isEqualTo(3);
    verify(basket).getTotalNumber();
  }
}
