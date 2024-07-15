package com.example.quiz.security;

import com.example.quiz.entity.User;
import com.example.quiz.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractAuthenticationFilterConfigurer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.util.Collections;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private static final Logger logger = LoggerFactory.getLogger(SecurityConfig.class);

//    private final UserService userService;
//    public SecurityConfig(UserService userService) {
//        this.userService = userService;
//    }
//    @Bean
//    public UserDetailsService userDetailsService() {
//        return username -> {
//            User user = userService.findByUsername(username);
//            if (user != null) {
//                return new org.springframework.security.core.userdetails.User(
//                        user.getUsername(),
//                        user.getPassword(),
//                        Collections.singletonList(new SimpleGrantedAuthority(user.getRole()))
//                );
//            } else {
//                throw new UsernameNotFoundException("User not found");
//            }
//        };
//    }


    @Autowired
    private CustomUserDetailService userDetailsService;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorizeRequests ->
                        authorizeRequests
                                .requestMatchers(new AntPathRequestMatcher("/login"), new AntPathRequestMatcher("/perform_login"),
                                        new AntPathRequestMatcher("/register"), new AntPathRequestMatcher("/logout")).permitAll()
                                .requestMatchers(new AntPathRequestMatcher("/admin/**")).hasRole("ADMIN")
                                .anyRequest().authenticated()
                )
                .formLogin(formLogin ->
                        formLogin
                                .loginPage("/login")
                                .loginProcessingUrl("/perform_login")
                                .defaultSuccessUrl("/quizzes", true)
                                .failureUrl("/login?error=true")
                                .permitAll()
                )

                .logout(logout ->
                        logout
                                .logoutUrl("/logout")
                                .logoutSuccessUrl("/login?logout")
                                .permitAll()
                );
        return http.build();
    }
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
//    }

}
