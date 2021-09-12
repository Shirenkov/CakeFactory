package com.cakefactory.domain;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BasketItem {

  private final Pastry pastry;
  private int quantity;

  public BigDecimal getTotal() {
    return pastry.getPrice().multiply(BigDecimal.valueOf(quantity));
  }

}
