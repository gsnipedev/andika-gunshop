package com.netheve.andikagunshop.dto.account;

import lombok.Data;

@Data
public class ChangePasswordDto {
    private String oldPassword;
    private String newPassword;
    private String newPasswordRepeat;
}
