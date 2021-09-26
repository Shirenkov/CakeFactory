package com.cakefactory;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
class EcomApplicationTest {

  @Test
  void contextLoads(ApplicationContext context) {
    assertThat(context).isNotNull();
  }

}
