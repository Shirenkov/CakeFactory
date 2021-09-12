package com.cakefactory.service;

import com.cakefactory.domain.BasketItem;
import java.util.List;

public interface OrderService {

  void placeOrder(String address, List<BasketItem> items);
}
