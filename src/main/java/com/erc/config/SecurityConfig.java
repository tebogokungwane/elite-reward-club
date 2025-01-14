package com.erc.config;

import com.erc.enumerators.Role;
import com.erc.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import java.util.Arrays;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    @Lazy
    private  UserServiceImpl userService;
    @Autowired
    private  JWTAuthFilter jwtAuthFilter;



    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf().disable()
                .authorizeRequests(request -> request
                        .antMatchers("/auth/**", "/public/**").permitAll()
                        .antMatchers("/api/v1/signin").permitAll()
                        .antMatchers("/api/v1/signUpAdmin").permitAll()
                        .antMatchers("/api/v1/signUpCustomer").permitAll()
                        .antMatchers("/api/v1/company/customers").permitAll()
                        .antMatchers("/api/v1/deleteCustomer/*").permitAll()
                        .antMatchers("/api/v1/users").permitAll()
                        .antMatchers("/api/v1/updateCustomer/**").permitAll()
                        .antMatchers("/api/v1/deleteCustomer").hasAnyRole(Role.ADMIN.name())
                        .antMatchers("/api/v1/updateCustomer/**").permitAll()
                        .antMatchers("/api/v1/auth/authenticate/").permitAll()
                        .antMatchers("/api/v1/profile").permitAll()
                        .antMatchers("/api/v1/profile/**").permitAll()
                        .antMatchers("/api/v1/updateCustomerRewardPoints/**").permitAll()
                        .antMatchers("/api/v1/updateMyCustomersTargetRewardPoint/**").permitAll()

                        .antMatchers("/api/v1/updateCompany/**").permitAll()
                        .antMatchers("/search/{productType}").permitAll() // Adjusted to include path variable
                        .antMatchers("/api/v1/forgot-password").permitAll()
                        .antMatchers("/filter").permitAll() // Adjusted to include path variable
                        .anyRequest().authenticated())
                //.oauth2ResourceServer(oauth2 -> oauth2.jwt())
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authenticationProvider(authenticationProvider())
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return httpSecurity.build();
    }


    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService((UserDetailsService) userService);
       // daoAuthenticationProvider.setUserDetailsService(ourUserDetailsService);
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        return daoAuthenticationProvider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public static AuthenticationManager authenticationManagerBean(
            AuthenticationProvider authenticationProvider) throws Exception {
        return new ProviderManager(Arrays.asList(authenticationProvider));
    }


}