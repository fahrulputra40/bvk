package com.bvk.demo.controller;

import com.bvk.demo.model.request.ItemRequest;
import com.bvk.demo.model.response.BaseResponse;
import com.bvk.demo.model.response.ItemResponse;
import com.bvk.demo.db.entity.ItemEntity;
import com.bvk.demo.db.repository.ItemRepository;
import com.bvk.demo.service.ItemService;
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
    private final ItemService itemService;
    @PostMapping
    public ResponseEntity<BaseResponse> createItem(@RequestBody ItemRequest itemRequest){
        return new ResponseEntity<>(BaseResponse.composeBaseResponse(itemService.createItem(itemRequest)), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<BaseResponse<List<ItemResponse>>> getItems(@RequestParam(value = "itemId", defaultValue = "") String itemId,
                                                                     @RequestParam(value = "itemName", defaultValue = "") String itemName){
        return ResponseEntity.ok(BaseResponse.composeBaseResponse(itemService.getItems(itemId, itemName)));
    }
    @PutMapping("/{itemId}")
    ResponseEntity<BaseResponse> updateItem(@RequestBody ItemRequest itemRequest, @PathVariable String itemId){
        return ResponseEntity.ok(BaseResponse.composeBaseResponse(itemService.updateItem(itemId, itemRequest)));
    }
    @DeleteMapping("/{itemId}")
    public ResponseEntity<BaseResponse> deleteItem(@PathVariable("itemId") String itemId){
        return ResponseEntity.ok(BaseResponse.composeBaseResponse(itemService.deleteItem(itemId)));
    }
}
