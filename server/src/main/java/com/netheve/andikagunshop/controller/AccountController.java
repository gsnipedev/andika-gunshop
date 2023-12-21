package com.netheve.andikagunshop.controller;

import com.netheve.andikagunshop.dto.account.ChangePasswordDto;
import com.netheve.andikagunshop.entity.AccountEntity;
import com.netheve.andikagunshop.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/account")
public class AccountController {
    @Autowired
    private AccountService accountService;

    @GetMapping("/all")
    public ResponseEntity<List<AccountEntity>> getAllAccount() {
        return ResponseEntity.ok(accountService.getAll());
    }

    @GetMapping
    public ResponseEntity<AccountEntity> getOne(@RequestHeader("Authorization") String bearerToken) {
        return ResponseEntity.ok(accountService.getByToken(bearerToken.replace("Bearer ", "")));
    }

    @PostMapping("/change-password")
    private ResponseEntity<AccountEntity> changePassword(@RequestHeader("Authorization") String bearerToken, @RequestBody ChangePasswordDto dto) {
        return ResponseEntity.ok(
                accountService.changePassword(
                        bearerToken.replace("Bearer ", ""),
                        dto
                )
        );
    }
}
