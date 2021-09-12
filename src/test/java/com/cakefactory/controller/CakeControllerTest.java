package com.cakefactory.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.htmlunit.MockMvcWebClientBuilder.mockMvcSetup;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.cakefactory.domain.Pastry;
import com.cakefactory.service.BasketService;
import com.cakefactory.service.CatalogService;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomNode;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import java.math.BigDecimal;
import java.util.List;
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
class CakeControllerTest {

  private WebClient client;
  @Autowired
  private MockMvc mockMvc;
  @MockBean
  private CatalogService catalogService;
  @MockBean
  private BasketService basketService;

  @BeforeEach
  void setUp() {
    this.client = mockMvcSetup(mockMvc).build();
  }

  @Test
  public void shouldReturnLandingPage() throws Exception {
    this.mockMvc.perform(get("/"))
        .andExpect(status().isOk())
        .andExpect(content().string(containsString("Cake Factory")));
  }

  @Test
  public void shouldReturnCatalog() throws Exception {
    String expectedTitle = "Sweet Cake";
    when(catalogService.findAll()).thenReturn(List.of(Pastry.builder()
        .id("abc")
        .title(expectedTitle)
        .price(new BigDecimal("2"))
        .build()));
    HtmlPage page = client.getPage("http://localhost/");
    assertThat(page.querySelectorAll(".item-title"))
        .anyMatch(domElement -> expectedTitle.equals(domElement.asText()));
  }

  @Test
  void shouldDisplayNumberOfItems() throws Exception {
    when(basketService.getNumberOfItems()).thenReturn(3);

    HtmlPage page = client.getPage("http://localhost/");

    DomNode totalElement = page.querySelector(".basket-total");
    assertThat(totalElement.asText()).isEqualTo("3");
  }

}
