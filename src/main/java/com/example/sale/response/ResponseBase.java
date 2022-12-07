package com.example.sale.response;

public class ResponseBase {
    public Integer errorCode;
    public String errorMessage;
    public Object body;

    public static ResponseBase getSuccess(Object body){
        ResponseBase responseBase = new ResponseBase();
        responseBase.errorCode = 200;
        responseBase.errorMessage = "Success";
        responseBase.body = body;
        return responseBase;
    }
}
