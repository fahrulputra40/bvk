package com.bvk.demo.model.response;

import lombok.Data;

@Data
public class BaseResponse <T>{
    private T data;
    private ErrorResponse error;
    public static SuccessResponse responseSuccess(){
        SuccessResponse successResponse = new SuccessResponse();
        successResponse.setStatus("success");
        return successResponse;
    }

    public static <T> BaseResponse composeBaseResponse(T data){
        BaseResponse<T> baseResponse = new BaseResponse<>();
        baseResponse.setData(data);
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
