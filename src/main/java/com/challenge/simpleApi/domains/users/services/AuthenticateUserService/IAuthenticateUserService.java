package com.challenge.simpleApi.domains.users.services.AuthenticateUserService;

import com.challenge.simpleApi.domains.users.dtos.JWTTokenDTO;
import com.challenge.simpleApi.domains.users.models.Users;
import org.springframework.security.core.userdetails.UserDetails;

public interface IAuthenticateUserService {
  public JWTTokenDTO execute(UserDetails user,String password);
}
