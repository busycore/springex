package com.challenge.simpleApi.shared.security;

import com.challenge.simpleApi.domains.users.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
  
  @Autowired
  private UsersService usersService;
  
  @Bean
  @Scope("singleton")
  public PasswordEncoder passwordEncoder(){
    //LocalFileUploadProvider -> Uploads to the system's default /uploads Folder
    //S3FileUploadProvider -> Supposed to upload the file's to Amazon's S3 CDN
    return new BCryptPasswordEncoder();
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
        .antMatchers("/users/**").hasAnyRole("USER","ADMIN")
        .antMatchers("/tasks/**").hasRole("ADMIN")
      //.anyRequest().authenticated()
      .and()
        .httpBasic();
  }


}
