package uz.pdp.appnewssite.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterDto {


    @NotNull(message = "FullName  bo'sh bo'lmasin")
    private String fullName;

    @NotNull(message = "UserName  bo'sh bo'lmasin")
    private String userName;

    @NotNull(message = "Parol  bo'sh bo'lmasin")
    private String password;

    @NotNull(message = "Parol takrori bo'sh bo'lmasin")
    private String prePassword;
}
