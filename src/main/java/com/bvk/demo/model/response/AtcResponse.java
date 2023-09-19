package com.bvk.demo.model.response;

import com.bvk.demo.model.request.AtcRequest;
import lombok.Data;

import java.util.List;

@Data
public class AtcResponse {
    private String cartId;
    private List<ItemResponse> item;
    private double totalPrice;
}
