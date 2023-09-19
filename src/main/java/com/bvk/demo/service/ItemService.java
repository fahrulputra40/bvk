package com.bvk.demo.service;

import com.bvk.demo.model.request.ItemRequest;
import com.bvk.demo.model.response.BaseResponse;
import com.bvk.demo.model.response.ItemResponse;

import java.util.List;

public interface ItemService {
    BaseResponse.SuccessResponse createItem(ItemRequest itemRequest);
    List<ItemResponse> getItems(String itemId, String itemName);

    BaseResponse.SuccessResponse updateItem(String itemId, ItemRequest itemRequest);
    BaseResponse.SuccessResponse deleteItem(String itemId);
}
