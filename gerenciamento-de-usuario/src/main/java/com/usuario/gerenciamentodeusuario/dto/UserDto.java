package com.usuario.gerenciamentodeusuario.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    @NotBlank
    private String userName;
    @NotBlank
    private String name;
    @NotBlank
    private String password;
    @NotBlank
    private String email;
    private String phoneNumber;

}
