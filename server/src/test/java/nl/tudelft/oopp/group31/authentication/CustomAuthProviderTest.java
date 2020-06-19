package nl.tudelft.oopp.group31.authentication;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

class CustomAuthProviderTest {

    @Test
    void testSupportsUsernamePassword() {
        CustomAuthProvider cap = new CustomAuthProvider();
        assertTrue(cap.supports(UsernamePasswordAuthenticationToken.class));
    }
}