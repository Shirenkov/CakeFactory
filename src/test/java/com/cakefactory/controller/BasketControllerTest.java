package com.cakefactory.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.htmlunit.MockMvcWebClientBuilder.mockMvcSetup;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.cakefactory.domain.Basket;
import com.cakefactory.domain.BasketItem;
import com.cakefactory.domain.Pastry;
import com.cakefactory.service.BasketService;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import java.math.BigDecimal;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
class BasketControllerTest {

  private WebClient client;
  @Autowired
  private MockMvc mockMvc;
  @MockBean
  private BasketService basketService;

  @BeforeEach
  void setUp() {
    this.client = mockMvcSetup(mockMvc).build();
  }

  @Test
  public void shouldReturnBasketPage() throws Exception {
    when(basketService.viewBasket()).thenReturn(new Basket());
    this.mockMvc.perform(get("/basket"))
        .andExpect(status().isOk())
        .andExpect(content().string(containsString("Your basket")));
  }

  @Test
  public void shouldReturnBasketItems() throws Exception {
    String expectedTitle = "Chocolate Croissant";
    Pastry pastry = Pastry.builder()
        .id("abc")
        .title(expectedTitle)
        .price(new BigDecimal("0.95"))
        .build();
    BasketItem item = new BasketItem(pastry, 1);
    Basket basket = new Basket();
    basket.addItem(item);
    when(basketService.viewBasket()).thenReturn(basket);
    when(basketService.getNumberOfItems()).thenReturn(1);
    HtmlPage page = client.getPage("http://localhost/basket");
    assertThat(page.querySelectorAll("td"))
        .anyMatch(domElement -> expectedTitle.equals(domElement.asText()));
  }

}
