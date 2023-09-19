package com.bvk.demo.controller;

import com.bvk.demo.model.request.AtcRequest;
import com.bvk.demo.model.request.ItemRequest;
import com.bvk.demo.model.response.BaseResponse;
import com.bvk.demo.model.response.ItemResponse;
import com.bvk.demo.service.AtcService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("atc")
@RequiredArgsConstructor
public class AtcController {

    private final AtcService atcService;
    @PostMapping
    public ResponseEntity<BaseResponse> createCart(@RequestBody AtcRequest atcRequest){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(BaseResponse.composeBaseResponse(atcService.addToCart(atcRequest)));
    }
    @GetMapping
    public ResponseEntity<BaseResponse<ItemResponse>> getCarts(@RequestParam(value = "cartId", defaultValue = "") String cartId){
        return ResponseEntity.ok(BaseResponse.composeBaseResponse(atcService.getCart(cartId)));
    }

    @PutMapping("/{cartId}")
    public ResponseEntity<BaseResponse> updateCart(@PathVariable("cartId") String cartId,@RequestBody AtcRequest atcRequest){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(BaseResponse.composeBaseResponse(atcService.updateCart(cartId,atcRequest)));
    }

    @DeleteMapping("/{cartId}")
    public ResponseEntity<BaseResponse> deleteCart(@PathVariable("cartId") String cartId){
        return ResponseEntity.ok(BaseResponse.composeBaseResponse(atcService.removeCart(cartId)));
    }
}
