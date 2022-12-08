package com.example.sale.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Objects;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "Common Api Response", description = "Common Api Response")
public class ApiResponse implements Serializable {

    @ApiModelProperty(value = "return code", required = true)
    private Integer errorCode;

    @ApiModelProperty(value = "return content", required = true)
    private String errorMessage;

    @ApiModelProperty(value = "return data", required = true)
    private Object body;

    public static ApiResponse of(Integer errorCode, String errorMessage, Object body) {
        return new ApiResponse(errorCode, errorMessage, body);
    }

    public static ApiResponse ofSuccess(Object data) {
        return ofResponse(Response.SUCCESS, data);
    }

    public static ApiResponse ofMessage(String message) {
        return of(Response.SUCCESS.getCode(), message, null);
    }

    public static ApiResponse ofResponse(Response response) {
        return ofResponse(response, null);
    }

    public static ApiResponse ofStatusAndMessage(int status, String message) {
        if (Objects.isNull(message)) {
            return ofResponse(Response.getResponseByCode(status));
        }

        return of(status, message, null);
    }

    public static ApiResponse ofResponse(Response response, Object data) {
        return of(response.getCode(), response.getMessage(), data);
    }
}
