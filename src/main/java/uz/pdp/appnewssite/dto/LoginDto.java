package uz.pdp.appnewssite.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginDto {

    @NotNull(message = "Username bo'sh bo'lmasin")
    private String username;


    @NotNull(message = "Parol bo'sh bo'lmasin")
    private String password;
}
