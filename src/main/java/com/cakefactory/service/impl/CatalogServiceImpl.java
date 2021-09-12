package com.cakefactory.service.impl;

import com.cakefactory.domain.Pastry;
import com.cakefactory.entity.PastryEntity;
import com.cakefactory.mapper.PastryMapper;
import com.cakefactory.repository.PastryRepository;
import com.cakefactory.service.CatalogService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CatalogServiceImpl implements CatalogService {

  private final PastryRepository pastryRepository;
  private final PastryMapper pastryMapper;

  @Override
  public List<Pastry> findAll() {
    List<PastryEntity> entities = (List<PastryEntity>) pastryRepository.findAll();
    return pastryMapper.map(entities);
  }

  @Override
  public Pastry findById(String id) {
    return pastryRepository.findById(id)
        .map(pastryMapper::map)
        .orElse(null);
  }

}
