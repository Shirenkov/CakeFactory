package com.cakefactory.repository;

import com.cakefactory.entity.PastryEntity;
import org.springframework.data.repository.CrudRepository;

public interface PastryRepository extends CrudRepository<PastryEntity, String> {
}
