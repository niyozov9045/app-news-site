package uz.pdp.appnewssite.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import uz.pdp.appnewssite.entity.enums.Permisson;
import uz.pdp.appnewssite.entity.template.AbsEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name= "users")
public class User extends AbsEntity implements UserDetails {

    @Column(nullable = false)
    private String fullName;

    @Column(nullable = false, unique = true)
    private String userName;

    @Column(nullable = false)
    private String password;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Role role;

    private boolean enabled;
    private boolean accountNonExpired = true;
    private boolean accountNonLocked = true;
    private boolean credentialsNonExpired = true;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<Permisson> permissonList = this.role.getPermissonList();
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        for (Permisson permisson : permissonList) {
            grantedAuthorities.add((GrantedAuthority) permisson::name);
        }
        return grantedAuthorities;
    }

    public User(String fullName, String userName, String password, Role role, boolean enabled) {
        this.fullName = fullName;
        this.userName = userName;
        this.password = password;
        this.role = role;
        this.enabled = enabled;
    }
}
