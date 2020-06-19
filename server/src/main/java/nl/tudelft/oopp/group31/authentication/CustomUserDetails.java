package nl.tudelft.oopp.group31.authentication;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import nl.tudelft.oopp.group31.entities.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


public class CustomUserDetails implements UserDetails {

    private String netid;
    private String password;
    private int type;
    private List<GrantedAuthority> authorities;

    private static final long serialVersionUID = 2576947287770806332L;

    /**
     * Constructor for CustomUserDetails with user as a param.
     *
     * @param user User
     */
    public CustomUserDetails(User user) {
        this.netid = user.getNetID();
        this.password = user.getPassword();
        this.type = user.getType();
        if (this.type == 1) {
            this.authorities = Collections.singletonList(new SimpleGrantedAuthority("ROLE_ADMIN"));
        } else if (this.type == 2) {
            this.authorities = Collections.singletonList(new SimpleGrantedAuthority("ROLE_EMPLOYEE"));
        } else {
            this.authorities = Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"));
        }
        System.out.println(this.netid + " " + this.password + " " + this.type + " " + this.authorities);
    }

    // Getters & Setters
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.netid;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
