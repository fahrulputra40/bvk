package com.bvk.demo.controller;

import com.bvk.demo.controller.request.ItemRequest;
import com.bvk.demo.controller.response.BaseResponse;
import com.bvk.demo.controller.response.ItemResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("atc")
public class AtcController {
    @PostMapping
    public ResponseEntity<BaseResponse> createCart(@RequestBody ItemRequest itemRequest){

    }

    @GetMapping
    public ResponseEntity<BaseResponse<ItemResponse>> getCarts(){

    }

    @GetMapping("/{cartId}")
    public ResponseEntity<BaseResponse<ItemResponse>> getCart(@RequestParam("cartId") String itemId){

    }

    @DeleteMapping("/{cartId}")
    public ResponseEntity<BaseResponse> deleteCart(@RequestParam("cartId") String itemId){

    }
}
