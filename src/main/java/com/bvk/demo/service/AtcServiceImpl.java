package com.bvk.demo.service;

import com.bvk.demo.db.entity.CartEntity;
import com.bvk.demo.db.entity.ItemEntity;
import com.bvk.demo.db.repository.CartRepository;
import com.bvk.demo.db.repository.ItemRepository;
import com.bvk.demo.exception.ServiceException;
import com.bvk.demo.model.mapper.ItemMapper;
import com.bvk.demo.model.mapper.ItemMapperImpl;
import com.bvk.demo.model.request.AtcRequest;
import com.bvk.demo.model.response.AtcResponse;
import com.bvk.demo.model.response.BaseResponse;
import com.bvk.demo.model.response.ItemResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AtcServiceImpl implements AtcService{

    private final CartRepository cartRepository;
    private final ItemRepository itemRepository;

    @Override
    @Transactional
    public BaseResponse.SuccessResponse addToCart(AtcRequest atcRequest) {
        List<ItemEntity> itemEntities = itemRepository.findAllById(atcRequest.getItemsId());
        CartEntity cartEntity = new CartEntity();
        cartEntity.setId(UUID.randomUUID().toString());
        cartEntity.setItemEntity(itemEntities);
        cartRepository.save(cartEntity);
        return BaseResponse.responseSuccess();
    }


    @Override
    @Transactional
    public BaseResponse.SuccessResponse updateCart(String cartId, AtcRequest atcRequest) {
        List<ItemEntity> itemEntities = itemRepository.findAllById(atcRequest.getItemsId());
        Optional<CartEntity> optionalCartEntity = cartRepository.findById(cartId);
        if(optionalCartEntity.isEmpty()) throw new ServiceException("Cart tidak tersedia");
        CartEntity cart = optionalCartEntity.get();
        List<String> cartItemIds = cart.getItemEntity().stream().map(ItemEntity::getId).collect(Collectors.toList());
        for (ItemEntity itemEntity: itemEntities){
            if(!cartItemIds.contains(itemEntity.getId())){
                cart.getItemEntity().add(itemEntity);
            }
        }
        cartRepository.save(cart);
        return BaseResponse.responseSuccess();
    }

    @Override
    public List<AtcResponse> getCart(String cartId) {
        List<CartEntity> atcResponses;
        if(cartId.isEmpty()) atcResponses = cartRepository.findAll();
        else atcResponses = cartRepository.findById(cartId).stream().collect(Collectors.toList());
        if(atcResponses.isEmpty()) throw new ServiceException("Cart tidak tersedia");
        ItemMapperImpl itemMapper = new ItemMapperImpl();
        return atcResponses.stream().map(mapToAtcResponse(itemMapper)).collect(Collectors.toList());
    }

    private Function<CartEntity, AtcResponse> mapToAtcResponse(ItemMapper itemMapper){
        return cart ->{
            AtcResponse atcResponse = new AtcResponse();
            atcResponse.setCartId(cart.getId());
            atcResponse.setItem(cart.getItemEntity().stream().map(d->{
                ItemResponse itemResponse = new ItemResponse();
                itemMapper.entityToDto(d, itemResponse);
                return itemResponse;
            }).collect(Collectors.toList()));
            atcResponse.setTotalPrice(cart.getItemEntity().stream().map(ItemEntity::getPrice).reduce((prev, next) -> prev+next).get());
            return atcResponse;
        };
    }

    @Override
    @Transactional
    public BaseResponse.SuccessResponse removeCart(String cartId) {
        try{
            cartRepository.deleteById(cartId);
        }catch (Exception e){
            new ServiceException("Cart tidak tersedia");
        }
        return BaseResponse.responseSuccess();
    }

}
