package com.cakefactory.repository;


import static org.assertj.core.api.Assertions.assertThat;

import com.cakefactory.entity.PastryEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

@DataJpaTest
@ActiveProfiles("test")
class PastryRepositoryTest {

  @Autowired
  private PastryRepository repository;

  @Test
  public void should_find_all_pastries() {
    Iterable<PastryEntity> pastries = repository.findAll();
    int numberOfPastries = 6;
    assertThat(pastries).hasSize(numberOfPastries);
  }
}