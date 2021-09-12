package com.cakefactory.controller;

import com.cakefactory.domain.Pastry;
import com.cakefactory.service.BasketService;
import com.cakefactory.service.CatalogService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequiredArgsConstructor
@RequestMapping("/")
public class CakeController {

  private final CatalogService catalogService;
  private final BasketService basketService;

  @GetMapping
  public ModelAndView getCatalog() {
    List<Pastry> pastries = catalogService.findAll();
    Map<String, Object> params = new HashMap<>();
    params.put("pastries", pastries);
    params.put("numberOfItems", basketService.getNumberOfItems());
    params.put("isHomePage", true);
    return new ModelAndView("index", params);
  }
}
