package com.cakefactory.service.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import com.cakefactory.domain.Pastry;
import com.cakefactory.entity.PastryEntity;
import com.cakefactory.repository.PastryRepository;
import com.cakefactory.service.CatalogService;
import java.math.BigDecimal;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
class CatalogServiceImplTest {

  @Autowired
  private CatalogService service;
  @MockBean
  private PastryRepository repository;

  @Test
  void testFindAll() {
    PastryEntity entity = new PastryEntity();
    entity.setId("abc");
    entity.setTitle("Cake");
    entity.setPrice(new BigDecimal("100.00"));
    Pastry result = Pastry.builder()
        .title("Cake")
        .price(new BigDecimal("100.00"))
        .build();
    Iterable<PastryEntity> entities = List.of(entity);
    when(repository.findAll()).thenReturn(entities);
    assertThat(service.findAll()).isEqualTo(List.of(result));
  }
}