package nl.tudelft.oopp.group31.authentication;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Collections;
import java.util.List;
import nl.tudelft.oopp.group31.entities.User;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;


class CustomUserDetailsTest {

    private final User user1 = new User("netID1", "pass1", 1, false);
    private final CustomUserDetails customUser1 = new CustomUserDetails(user1);

    private final User userAdmin = new User("admin", "admin", 1, false);
    private final CustomUserDetails customUserAdmin = new CustomUserDetails(userAdmin);

    private final User userEmployee = new User("employee", "employee", 2, false);
    private final CustomUserDetails customUserEmployee = new CustomUserDetails(userEmployee);

    private final User userNormal = new User("user", "user", 3, false);
    private final CustomUserDetails customUserNormal = new CustomUserDetails(userNormal);

    private final User userUnknownType = new User("user", "user", 200, false);
    private final CustomUserDetails customUserUnknownType = new CustomUserDetails(userUnknownType);

    @Test
    void testGetAuthoritiesAdmin() {
        List<GrantedAuthority> expectedAuthorities = Collections.singletonList(new SimpleGrantedAuthority("ROLE_ADMIN"));
        assertEquals(customUserAdmin.getAuthorities(), expectedAuthorities);
    }

    @Test
    void testGetAuthoritiesEmployee() {
        List<GrantedAuthority> expectedAuthorities = Collections.singletonList(new SimpleGrantedAuthority("ROLE_EMPLOYEE"));
        assertEquals(customUserEmployee.getAuthorities(), expectedAuthorities);
    }

    @Test
    void testGetAuthoritiesNormal() {
        List<GrantedAuthority> expectedAuthorities = Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"));
        assertEquals(customUserNormal.getAuthorities(), expectedAuthorities);
        assertEquals(customUserUnknownType.getAuthorities(), expectedAuthorities);
    }

    @Test
    void testGetPassword() {
        assertEquals(customUser1.getPassword(),"pass1");
    }

    @Test
    void testGetUsername() {
        assertEquals(customUser1.getUsername(),"netID1");
    }

    @Test
    void testIsAccountNonExpired() {
        assertTrue(customUser1.isAccountNonExpired());
    }

    @Test
    void testIsAccountNonLocked() {
        assertTrue(customUser1.isAccountNonLocked());
    }

    @Test
    void testIsCredentialsNonExpired() {
        assertTrue(customUser1.isCredentialsNonExpired());
    }

    @Test
    void testIsEnabled() {
        assertTrue(customUser1.isEnabled());
    }
}