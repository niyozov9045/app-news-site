package uz.pdp.appnewssite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.appnewssite.dto.ApiResponse;
import uz.pdp.appnewssite.dto.RegisterDto;
import uz.pdp.appnewssite.dto.UserDto;
import uz.pdp.appnewssite.service.AuthService;
import uz.pdp.appnewssite.service.UserService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/user")
public class UserController {


    @Autowired
    UserService userService;

    // user ro'yxatdan o'tqazish
    @PostMapping("/register")
    public HttpEntity<?> registerUser(@Valid @RequestBody UserDto userDto) {
        ApiResponse apiResponse = userService.addUser(userDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }


    

}
