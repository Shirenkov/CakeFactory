package com.cakefactory.mapper;

import com.cakefactory.domain.Pastry;
import com.cakefactory.entity.PastryEntity;
import java.util.List;
import org.mapstruct.Mapper;

@Mapper
public interface PastryMapper {

  Pastry map(PastryEntity source);

  List<Pastry> map(List<PastryEntity> entities);
}
