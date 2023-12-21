package com.netheve.andikagunshop.service;

import com.netheve.andikagunshop.dto.account.ChangePasswordDto;
import com.netheve.andikagunshop.entity.AccountEntity;
import com.netheve.andikagunshop.service.enums.TokenType;

import java.util.List;

public interface AccountService {
    public AccountEntity getByToken(String token);

    public AccountEntity getByToken(String token, TokenType type);

    AccountEntity getByUsername(String username);

    List<AccountEntity> getAll();

    AccountEntity changePassword(String token, ChangePasswordDto dto);
}
