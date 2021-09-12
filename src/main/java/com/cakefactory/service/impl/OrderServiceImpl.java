package com.cakefactory.service.impl;

import com.cakefactory.domain.BasketItem;
import com.cakefactory.service.OrderService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderServiceImpl implements OrderService {

  @Override
  public void placeOrder(String address, List<BasketItem> items) {
    log.info("Order was placed: address: {}, items: {}", address, items);
  }

}
