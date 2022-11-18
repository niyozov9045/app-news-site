package uz.pdp.appnewssite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appnewssite.dto.ApiResponse;
import uz.pdp.appnewssite.dto.LoginDto;
import uz.pdp.appnewssite.dto.RegisterDto;
import uz.pdp.appnewssite.entity.User;
import uz.pdp.appnewssite.security.JwtProvider;
import uz.pdp.appnewssite.service.AuthService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class AuthController {


    @Autowired
    AuthService authService;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    JwtProvider jwtProvider;

    // user ro'yxatdan o'tqazish
    @PostMapping("/register")
    public HttpEntity<?> registerUser(@Valid @RequestBody RegisterDto registerDto) {
        ApiResponse apiResponse = authService.registerUser(registerDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }


    @PostMapping("/login")
    public HttpEntity<?> loginUser(@Valid @RequestBody LoginDto loginDto) {
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword()));
        User user = (User) authenticate.getPrincipal();
        String token = jwtProvider.getUsernameFromToken(user.getUsername(), user.getRole());
        return ResponseEntity.ok(token);
    }


}
