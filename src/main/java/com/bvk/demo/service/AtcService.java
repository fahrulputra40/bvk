package com.bvk.demo.service;

import com.bvk.demo.model.request.AtcRequest;
import com.bvk.demo.model.response.AtcResponse;
import com.bvk.demo.model.response.BaseResponse;

import java.util.List;

public interface AtcService {
    BaseResponse.SuccessResponse addToCart(AtcRequest atcRequest);
    BaseResponse.SuccessResponse updateCart(String cartId, AtcRequest atcRequest);
    List<AtcResponse> getCart(String cartId);
    BaseResponse.SuccessResponse removeCart(String cartId);
}
