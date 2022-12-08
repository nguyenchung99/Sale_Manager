package com.example.sale.response;

public enum Response {
    SUCCESS(200, "SUCCESS"),
    NOK_APPID_NOT_EXIST(400, "NOK_APPID_NOT_EXIST"),
    NOK_VERSION_NOT_SUPPORT(401, "NOK_VERSION_NOT_SUPPORT"),
    NOK_DEVICEID_NOT_FOUND(402, "NOK_DEVICEID_NOT_FOUND"),
    NOK_REFRESH_TOKEN_EXPIRED(403, "NOK_REFRESH_TOKEN_EXPIRED"),
    NOK_PARAMETER_BAD_VALUE(405, "NOK_INVALID_PARAMETER"),
    NOK_BAD_SYNTAX(406, "NOK_BAD_SYNTAX"),
    NOK_DEVICE_NOT_FOUND(407, "NOK_DEVICE_NOT_FOUND"),
    NOK_REQUEST_TIMEOUT(408, "NOK_REQUEST_TIMEOUT"),
    NOK_DEVICE_NOT_CONNECTED(409, "NOK_DEVICE_NOT_CONNECTED"),
    NOK_UNAUTHORIZED(410, "NOK_UNAUTHORIZED"),
    SERVER_INTERNAL_ERROR(500, "SERVER_INTERNAL_ERROR");

    private final int code;
    private final String message;

    private Response(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return this.code;
    }

    public String getMessage() {
        return this.message;
    }

    public static Response getResponseByCode(int code) {
        Response[] var1 = values();
        int var2 = var1.length;

        for(int var3 = 0; var3 < var2; ++var3) {
            Response response = var1[var3];
            if (response.code == code) {
                return response;
            }
        }

        return SERVER_INTERNAL_ERROR;
    }
}