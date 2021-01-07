package com.cakefactory;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class CakeController {

  @GetMapping
  public String showDesignForm(Model model) {
    return "index";
  }
}
