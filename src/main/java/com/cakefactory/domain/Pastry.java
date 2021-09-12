package com.cakefactory.domain;

import java.math.BigDecimal;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Pastry {

  private String id;
  private String title;
  private BigDecimal price;
}
