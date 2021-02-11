package com.challenge.simpleApi.domains.users.services.AuthenticateUserService;

import com.challenge.simpleApi.domains.users.dtos.JWTTokenDTO;
import com.challenge.simpleApi.domains.users.models.Users;
import com.challenge.simpleApi.shared.errors.exceptions.InvalidPassWordException;
import com.challenge.simpleApi.shared.security.JWT.JWTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticateUserService implements IAuthenticateUserService{
  @Autowired
  private JWTService jwtService;

  @Autowired
  private PasswordEncoder passwordEncoder;
  
  @Override
  public JWTTokenDTO execute(UserDetails user,String password) {
    boolean isCorrectPassword = passwordEncoder.matches(password,user.getPassword());
    if(!isCorrectPassword){
      throw new InvalidPassWordException("Password is incorrect");
    }
    
    Users user2 = Users.builder().username(user.getUsername()).password(user.getPassword()).build();

    String token = this.jwtService.GenerateToken(user2);
    
    return JWTTokenDTO.builder().username(user2.getUsername()).token(token).build();
  }
}
