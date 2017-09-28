package readinglist.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import readinglist.repository.ReaderRepository;

/**
 * Created by naaminicharles on 9/28/17.
 */
/*The @Profile annotation used here requires that the “production” profile be active at
runtime for this configuration to be applied, otherwise ignored*/
@Profile("production")
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private ReaderRepository readerRepository;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                /*require READER access*/
                .antMatchers("/").access("hasRole('READER')")
                .antMatchers("/**").permitAll()

                .and()

                .formLogin()
                /*set login form path*/
                .loginPage("/login")
                .failureUrl("/login?error=true");
    }

    @Override
    protected void configure(
            AuthenticationManagerBuilder auth) throws Exception {

        /*define custom UserDetailsService*/
            auth.userDetailsService(new UserDetailsService() {

                    @Override
                    public UserDetails loadUserByUsername(String username)
                            throws UsernameNotFoundException {
                        return readerRepository.findOne(username);
                    }
                });
    }
}
