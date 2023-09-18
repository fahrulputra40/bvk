package com.bvk.demo.controller.response;

import lombok.Data;

@Data
public class BaseResponse <T>{
    private T data;
    private ErrorResponse error;
    public static BaseResponse responseSuccess(){
        BaseResponse<SuccessResponse> baseResponse = new BaseResponse<>();
        SuccessResponse successResponse = new SuccessResponse();
        successResponse.setStatus("success");
        baseResponse.setData(successResponse);
        return baseResponse;
    }
    @Data
    public static class SuccessResponse{
        private String status;
    }

    @Data
    public static class ErrorResponse{
        private String reason;
    }
}
