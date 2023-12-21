package com.netheve.andikagunshop.service;

import com.netheve.andikagunshop.dto.account.CreateAccountDto;
import com.netheve.andikagunshop.dto.auth.LoginRequest;
import com.netheve.andikagunshop.dto.auth.LoginResponse;
import com.netheve.andikagunshop.entity.AccountEntity;
import com.netheve.andikagunshop.entity.enums.AccountRank;

public interface AuthService {
    LoginResponse login(LoginRequest request);

    boolean validateToken(String authToken);

    boolean validTokenAs(String authToken, AccountRank rank);

    AccountEntity register(CreateAccountDto request);
}
