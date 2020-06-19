package nl.tudelft.oopp.group31.authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    CustomUserDetailsService userDetailsService;

    /**
     * Configures the security with the current User details.
     * @param auth An {@link AuthenticationManagerBuilder} object
     * @throws Exception if an error occurs during authentication
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
        auth.authenticationProvider(authProvider());
    }

    /**
     * Sets up endpoint access rules for the different roles of users we have.
     * @param http A {@link HttpSecurity} object
     * @throws Exception if an error occurs during request authorization
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers(HttpMethod.POST, "/user/*").permitAll()
                .antMatchers("/building/all").hasAnyRole("ADMIN", "USER", "EMPLOYEE")
                .antMatchers("/room/all").hasAnyRole("ADMIN", "USER", "EMPLOYEE")
                .antMatchers("/reservation/*").hasAnyRole("ADMIN", "USER", "EMPLOYEE")
                .antMatchers("/user/login").hasAnyRole("ADMIN", "USER", "EMPLOYEE")
                .antMatchers("/*").hasRole("ADMIN")
                .and().httpBasic();

        http.csrf().disable();
    }

    /**
     * Set up paths that will be ignored.
     * @param web A {@link WebSecurity} object
     */
    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers("/user/add");
    }

    /**
     * Create a password encode that uses strong cryptographic hashing.
     * @return A strength-11 {@link BCryptPasswordEncoder} for the security configuration.
     */
    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder(11);
    }

    /**
     * Creates a new CustomAuthProvider instance using the current user's userDetailsService.
     * @return A new {@link CustomAuthProvider}
     */
    @Bean
    public DaoAuthenticationProvider authProvider() {
        final CustomAuthProvider authProvider = new CustomAuthProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(getPasswordEncoder());
        return authProvider;
    }

}
