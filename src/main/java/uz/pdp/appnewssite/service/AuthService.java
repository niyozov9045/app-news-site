package uz.pdp.appnewssite.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import uz.pdp.appnewssite.dto.ApiResponse;
import uz.pdp.appnewssite.dto.RegisterDto;

import uz.pdp.appnewssite.entity.User;
import uz.pdp.appnewssite.exception.ResourceNotFoundException;
import uz.pdp.appnewssite.repository.RoleRepository;
import uz.pdp.appnewssite.repository.UserRepository;
import uz.pdp.appnewssite.utils.AppConstants;

@Service
public class AuthService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

    public ApiResponse registerUser(@RequestBody RegisterDto registerDto) {
        if (!registerDto.getPassword().equals(registerDto.getPrePassword())) {
            return new ApiResponse("Parollar mos emas", false);
        }

        if (userRepository.existsByUsername((registerDto.getUserName())))
            return new ApiResponse("Bunday username mavjud", false);
        User user = new
                User(
                registerDto.getFullName(),
                registerDto.getUserName(),
                passwordEncoder.encode(registerDto.getPassword()),
                roleRepository.findByName(AppConstants.User).orElseThrow(() -> new ResourceNotFoundException("role", "name", AppConstants.User)),
                true
        );
        userRepository.save(user);
        return new ApiResponse("Muvaffaqqiyatli ro'yxatdan o    'tdinggiz", true);
    }

    public UserDetails loadUserByUsername(String userName) {
        return userRepository.findByUserName(userName).orElseThrow(() -> new UsernameNotFoundException(userName));

    }
}
