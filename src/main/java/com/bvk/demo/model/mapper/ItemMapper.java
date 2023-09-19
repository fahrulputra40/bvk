package com.bvk.demo.model.mapper;

import com.bvk.demo.db.entity.ItemEntity;
import com.bvk.demo.model.request.ItemRequest;
import com.bvk.demo.model.response.ItemResponse;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper
public interface ItemMapper {
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void dtoToEntity(ItemRequest itemRequest, @MappingTarget ItemEntity itemEntity);
    void entityToDto(ItemEntity itemEntity, @MappingTarget ItemResponse itemRequest);
}
