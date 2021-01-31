package com.cakefactory.entity;

import java.math.BigDecimal;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "catalog")
public class PastryEntity {

  @Id
  private String id;
  private String title;
  private BigDecimal price;
}
