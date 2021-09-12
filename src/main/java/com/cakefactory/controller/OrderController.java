package com.cakefactory.controller;

import com.cakefactory.service.BasketService;
import com.cakefactory.service.OrderService;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequiredArgsConstructor
@RequestMapping("/order")
public class OrderController {

  private final OrderService orderService;
  private final BasketService basketService;

  @GetMapping
  public ModelAndView getOrderInfo() {
    Map<String, Object> params = new HashMap<>();
    params.put("numberOfItems", basketService.getNumberOfItems());
    return new ModelAndView("order", params);
  }

  @PostMapping("/place")
  public String placeOrder(String addressLine1, String addressLine2, String postcode) {
    String address = String.join(", ", addressLine1, addressLine2, postcode);
    orderService.placeOrder(address, basketService.viewBasket().getItems());
    basketService.clearBasket();
    return "redirect:/order";
  }

}
