package nl.tudelft.oopp.group31.authentication;

import java.util.Optional;

import nl.tudelft.oopp.group31.entities.User;
import nl.tudelft.oopp.group31.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    /**
     * Allows the authentication to load the user details and work from there.
     * @param netid The NetID (username) of the user.
     * @return User details of authenticated User
     */
    @Override
    public UserDetails loadUserByUsername(String netid) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByNetID(netid);

        user.orElseThrow(() -> new UsernameNotFoundException("Username is not in the database. Try making a new account"));

        return user.map(CustomUserDetails::new).get();
    }
}
