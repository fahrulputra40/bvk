package com.bvk.demo.service;

import com.bvk.demo.db.entity.ItemEntity;
import com.bvk.demo.db.repository.ItemRepository;
import com.bvk.demo.exception.ServiceException;
import com.bvk.demo.model.mapper.ItemMapperImpl;
import com.bvk.demo.model.request.ItemRequest;
import com.bvk.demo.model.response.BaseResponse;
import com.bvk.demo.model.response.ItemResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ItemServiceImpl implements ItemService{
    private final ItemRepository itemRepository;
    @Override
    @Transactional
    public BaseResponse.SuccessResponse createItem(ItemRequest itemRequest) {
        ItemEntity itemEntity = new ItemEntity();
        new ItemMapperImpl().dtoToEntity(itemRequest, itemEntity);
        itemEntity.setId(UUID.randomUUID().toString());
        itemRepository.save(itemEntity);
        return BaseResponse.responseSuccess();
    }
    @Override
    public List<ItemResponse> getItems(String itemId, String itemName) {
        List<ItemEntity> responses;
        if(!itemId.isEmpty()) responses = itemRepository.findById(itemId).stream().findAny().stream().collect(Collectors.toList());
        else if(!itemName.isEmpty()) responses = itemRepository.findByNameContaining(itemName);
        else responses = itemRepository.findAll();

        if(responses.isEmpty()) throw new ServiceException("Item tidak tersedia");
        ItemMapperImpl itemMapper = new ItemMapperImpl();
        return responses.stream().map(d->{
            ItemResponse itemResponse = new ItemResponse();
            itemMapper.entityToDto(d, itemResponse);
            return itemResponse;
        }).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public BaseResponse.SuccessResponse updateItem(String itemId, ItemRequest itemRequest) {
        Optional<ItemEntity> optionalItemEntity  = itemRepository.findById(itemId);
        if(optionalItemEntity.isEmpty()) throw new ServiceException("Item tidak tersedia");
        ItemEntity itemEntity = optionalItemEntity.get();
        new ItemMapperImpl().dtoToEntity(itemRequest, itemEntity);
        itemRepository.save(itemEntity);
        return BaseResponse.responseSuccess();
    }
    @Override
    public BaseResponse.SuccessResponse deleteItem(String itemId) {
        try {
            itemRepository.deleteById(itemId);
        }catch (Exception e){
            throw new ServiceException("Item tidak ditemukan");
        }
        return BaseResponse.responseSuccess();
    }
}
