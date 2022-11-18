package uz.pdp.appnewssite.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import uz.pdp.appnewssite.entity.Role;
import uz.pdp.appnewssite.entity.User;
import uz.pdp.appnewssite.entity.enums.Permisson;
import uz.pdp.appnewssite.repository.RoleRepository;
import uz.pdp.appnewssite.repository.UserRepository;
import uz.pdp.appnewssite.utils.AppConstants;

import java.util.Arrays;

import static uz.pdp.appnewssite.entity.enums.Permisson.*;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    PasswordEncoder passwordEncoder;


    @Value("${spring.sql.init.mode}")
    private String initialMode;


    @Override

    public void run(String... args) {
        if (initialMode.equals("always")) {
            Permisson[] permissonsList = Permisson.values();
            Role admin = roleRepository.save(new Role(
                    AppConstants.Admin,
                    Arrays.asList(permissonsList),
                    "Sestema egasi"
            ));
            Role user = roleRepository.save(new Role(
                    AppConstants.User,
                    Arrays.asList(ADD_COMMENT, EDIT_COMMENT, DELETE_MY_COMMENT),
                    "Oddiy foydalanuvchi "
            ));

            userRepository.save(new User(
                    "Admin",
                    "admin",
                    passwordEncoder.encode("admin123"),
                    admin,
                    true
            ));
            userRepository.save(new User(
                    "User",
                    "user",
                    passwordEncoder.encode("user123"),
                    user,
                    true
            ));
        }
    }
}
