package nl.tudelft.oopp.group31.authentication;

import java.util.Optional;

import nl.tudelft.oopp.group31.entities.User;
import nl.tudelft.oopp.group31.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

public class CustomAuthProvider extends DaoAuthenticationProvider {

    @Autowired
    private UserRepository userRepository;

    /**
     * Authenticates the user if the right credentials are used and forwards request.
     * @param auth {@link Authentication} object for user
     * @throws AuthenticationException if incorrect credentials are supplied
     */
    @Override
    public Authentication authenticate(Authentication auth) throws AuthenticationException {
        final Optional<User> user = userRepository.findByNetID(auth.getName());
        if (!user.isPresent()) {
            throw new BadCredentialsException("Wrong credentials.");
        }

        final Authentication res = super.authenticate(auth);
        return new UsernamePasswordAuthenticationToken(user.get(), res.getCredentials(), res.getAuthorities());
    }

    /**
     * Determines whether or not the authentication is supported.
     * @param authentication candidate object
     * @return boolean that represents if the authentication type is supported.
     */
    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
