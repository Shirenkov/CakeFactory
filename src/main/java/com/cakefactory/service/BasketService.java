package com.cakefactory.service;

import com.cakefactory.domain.Basket;

public interface BasketService {

  void addToBasket(String id);

  void removeFromBasket(String id);

  void clearBasket();

  Basket viewBasket();

  int getNumberOfItems();

}
