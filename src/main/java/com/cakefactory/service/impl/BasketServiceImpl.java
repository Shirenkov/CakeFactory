package com.cakefactory.service.impl;

import com.cakefactory.domain.Basket;
import com.cakefactory.domain.BasketItem;
import com.cakefactory.service.BasketService;
import com.cakefactory.service.CatalogService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BasketServiceImpl implements BasketService {

  private final Basket basket;
  private final CatalogService catalogService;

  @Override
  public void addToBasket(String id) {
    basket.getItems().stream()
        .filter(item -> item.getPastry().getId().equals(id))
        .findFirst()
        .ifPresentOrElse(
            basketItem -> basketItem.setQuantity(basketItem.getQuantity() + 1),
            () -> basket.addItem(new BasketItem(catalogService.findById(id), 1))
        );
  }

  @Override
  public void removeFromBasket(String id) {
    basket.getItems().stream()
        .filter(item -> item.getPastry().getId().equals(id))
        .findFirst()
        .ifPresent(item -> {
              int currentQuantity = item.getQuantity() - 1;
              if (currentQuantity == 0) {
                basket.removeItem(item);
                return;
              }
              item.setQuantity(currentQuantity);
            }
        );
  }

  @Override
  public void clearBasket() {
    basket.clearItems();
  }

  @Override
  public Basket viewBasket() {
    return basket;
  }

  @Override
  public int getNumberOfItems() {
    return basket.getTotalNumber();
  }
}
