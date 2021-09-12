package com.cakefactory.controller;

import com.cakefactory.domain.Basket;
import com.cakefactory.service.BasketService;
import java.util.HashMap;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequiredArgsConstructor
@RequestMapping("/basket")
public class BasketController {

  private final BasketService basketService;

  @PostMapping("/add")
  public String addToBasket(String id) {
    basketService.addToBasket(id);
    return "redirect:/";
  }

  @PostMapping("/remove")
  public String removeFromBasket(String id) {
    basketService.removeFromBasket(id);
    return "redirect:/basket";
  }

  @GetMapping
  public ModelAndView viewBasket() {
    Basket basket = basketService.viewBasket();
    Map<String, Object> params = new HashMap<>();
    params.put("basketItems", basket.getItems());
    params.put("numberOfItems", basketService.getNumberOfItems());
    params.put("isBasketPage", true);
    return new ModelAndView("basket", params);
  }
}
