package com.practicesoftwaretesting;

import lombok.Data;

@Data
public class LoginResponse {

    private String accessToken;
    private String tokenType;
    private int expiresIn;
}
