package com.cakefactory.domain;

import static org.springframework.context.annotation.ScopedProxyMode.TARGET_CLASS;
import static org.springframework.web.context.WebApplicationContext.SCOPE_SESSION;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(value = SCOPE_SESSION, proxyMode = TARGET_CLASS)
public class Basket {

  private final List<BasketItem> items = new CopyOnWriteArrayList<>();

  public List<BasketItem> getItems() {
    return items;
  }

  public void addItem(BasketItem basketItem) {
    items.add(basketItem);
  }

  public void removeItem(BasketItem basketItem) {
    items.remove(basketItem);
  }

  public void clearItems() {
    items.clear();
  }

  public int getTotalNumber() {
    return items.stream().mapToInt(BasketItem::getQuantity).sum();
  }

}
