package com.example.sale.response;

import lombok.Data;

@Data
public class TokenResponse{
    private String acessToken;
    private String refreshToken;
}
