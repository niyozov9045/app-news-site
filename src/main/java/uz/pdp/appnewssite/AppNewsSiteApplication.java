package uz.pdp.appnewssite;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AppNewsSiteApplication {
    public static void main(String[] args) {
        SpringApplication.run(AppNewsSiteApplication.class, args);
    }


    // Maqola tizimga joylanadi, uni foydalanuvchilar o'qiydi, shu maqolani tizimda joylashishini  admin belgilaydi
    // biz maqolani o'qiymiz va usha maqolaga foydalanuvchila koment yozadi. Koment foydalanuvhi o'chira olishi mumkin

}
