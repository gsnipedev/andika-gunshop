package com.netheve.andikagunshop.service.impl;

import com.netheve.andikagunshop.common.crypto.BCrypt;
import com.netheve.andikagunshop.dto.account.ChangePasswordDto;
import com.netheve.andikagunshop.entity.AccountEntity;
import com.netheve.andikagunshop.repository.AccountRepository;
import com.netheve.andikagunshop.service.AccountService;
import com.netheve.andikagunshop.service.enums.TokenType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountRepository accountRepository;

    @Override
    public AccountEntity getByToken(String token) {
        return accountRepository.findByAuthToken(token).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid Token")
        );
    }

    @Override
    public AccountEntity getByToken(String token, TokenType type) {
        switch (type) {
            case NO_SCHEME -> {
                return accountRepository.findByAuthToken(token).orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid Token")
                );
            }
            case BEARER -> {
                return accountRepository.findByAuthToken(token.replace("Bearer ", "")).orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid Token")
                );
            }
            case BASIC -> {
                return accountRepository.findByAuthToken(token.replace("Basic ", "")).orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid Token")
                );
            }
            default -> throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid token scheme");
        }
    }

    @Override
    public AccountEntity getByUsername(String username) {
        return accountRepository.findByUsername(username).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Account with specific username not found")
        );
    }

    @Override
    public List<AccountEntity> getAll() {
        return accountRepository.findAll();
    }

    @Override
    public AccountEntity changePassword(String token, ChangePasswordDto dto) {
        var account = accountRepository.findByAuthToken(token).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid Token")
        );

        account.setPassword(BCrypt.hashpw(dto.getNewPassword(), BCrypt.gensalt()));

        return accountRepository.save(account);
    }
}
