package uz.pdp.appnewssite.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import uz.pdp.appnewssite.entity.User;
import uz.pdp.appnewssite.exception.ForbittenException;

@Component
@Aspect
public class CheckPermissionExcutor {

    @Before(value = "@annotation(chekPermission)")
    public void chekUserPermissionMyMethod(ChekPermission chekPermission) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        boolean exist = false;
        for (GrantedAuthority authority : user.getAuthorities()) {
            if (authority.getAuthority().equals(chekPermission.value())) {
                exist = true;
                break;
            }
        }
        if (!exist)
            throw new ForbittenException(chekPermission.value(), "Ruxsat yo'q");
    }
}
