package com.bvk.demo.controller.response;

import com.bvk.demo.controller.request.AtcRequest;
import lombok.Data;

@Data
public class AtcResponse extends AtcRequest {
    private ItemResponse item;
}
