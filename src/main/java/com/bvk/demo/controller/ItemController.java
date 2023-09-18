package com.bvk.demo.controller;

import com.bvk.demo.controller.request.ItemRequest;
import com.bvk.demo.controller.response.BaseResponse;
import com.bvk.demo.controller.response.ItemResponse;
import com.bvk.demo.db.entity.ItemEntity;
import com.bvk.demo.db.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("item")
@RequiredArgsConstructor
public class ItemController {

    private final ItemRepository itemRepository;

    @PostMapping
    public ResponseEntity<BaseResponse> createItem(@RequestBody ItemRequest itemRequest){
        ItemEntity itemEntity = itemRequest;
        itemEntity.setId("");
        itemRepository.save(itemEntity);
        return new ResponseEntity<>(BaseResponse.responseSuccess(), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<BaseResponse<List<ItemResponse>>> getItems(){
        BaseResponse<List<ItemResponse>> itemResponse = new BaseResponse<>();
        itemResponse.setData(itemRepository.findAll().stream().map(d->(ItemResponse)d).collect(Collectors.toList()));
        return ResponseEntity.ok(itemResponse);
    }

    @GetMapping("/{itemId}")
    public ResponseEntity<BaseResponse<ItemResponse>> getItem(@RequestParam("itemId") String itemId){
        BaseResponse<ItemResponse> itemResponseBaseResponse = new BaseResponse<>();
        Optional<ItemEntity> itemEntity = itemRepository.findById(itemId);
        if(itemEntity.isEmpty()){
            BaseResponse.ErrorResponse errorResponse = new BaseResponse.ErrorResponse();
            errorResponse.setReason("Item tidak tersedia");
            itemResponseBaseResponse.setError(errorResponse);
        }else
            itemResponseBaseResponse.setData((ItemResponse)itemEntity.get());
        return ResponseEntity.ok(itemResponseBaseResponse);
    }

    @DeleteMapping("/{itemId}")
    public ResponseEntity<BaseResponse> deleteItem(@RequestParam("itemId") String itemId){
        itemRepository.deleteById(itemId);
        return ResponseEntity.ok(BaseResponse.responseSuccess());
    }
}
