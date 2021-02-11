package com.challenge.simpleApi.shared.security;

import com.challenge.simpleApi.domains.users.services.UsersService;
//import com.challenge.simpleApi.shared.security.JWT.JWTAuthFilter;
import com.challenge.simpleApi.shared.security.JWT.JWTAuthFilter;
import com.challenge.simpleApi.shared.security.JWT.JWTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.OncePerRequestFilter;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
  
  @Autowired
  private UsersService usersService;
  
  @Autowired
  private JWTService jwtService;
  
  @Bean
  @Scope("singleton")
  public PasswordEncoder passwordEncoder(){
    return new BCryptPasswordEncoder();
  }
  
  public OncePerRequestFilter jwtFilter(){
    return new JWTAuthFilter(jwtService,usersService);
  }
  
  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    //Example of in memory authentication
    //    auth.inMemoryAuthentication()
    //      .passwordEncoder(passwordEncoderImpl())
    //      .withUser("fulano")
    //      .password(passwordEncoder().encode("admin123"))
    //      .roles("USER","ADMIN");
    
    auth
      .userDetailsService(usersService)
      .passwordEncoder(passwordEncoder());
    
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.
      csrf().disable() //Disable the CSRF cause we are working in a stateless model
      .authorizeRequests()
        .antMatchers(HttpMethod.POST,"/users/**").permitAll()
        .antMatchers(HttpMethod.POST,"/users/authenticate").permitAll()
        .antMatchers("/users/**").hasAnyRole("USER","ADMIN")
        .antMatchers("/tasks/**").hasRole("ADMIN")
      .anyRequest().authenticated()
      .and()
        .sessionManagement()
        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)// Now the application is stateless.every request must contain our jwt token
      .and()
        //.httpBasic();
        .addFilterBefore( jwtFilter(), UsernamePasswordAuthenticationFilter.class);//Execute jwtFilter before the UsernamePasswordAuthenticationFilter
                                                                                  // We are already creating an token to this second filter in our service
  }


}
