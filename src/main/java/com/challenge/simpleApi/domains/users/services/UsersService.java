package com.challenge.simpleApi.domains.users.services;

import com.challenge.simpleApi.domains.users.dtos.AuthenticationDTO;
import com.challenge.simpleApi.domains.users.dtos.JWTTokenDTO;
import com.challenge.simpleApi.domains.users.models.Users;
import com.challenge.simpleApi.domains.users.services.AuthenticateUserService.IAuthenticateUserService;
import com.challenge.simpleApi.domains.users.services.CreateUsersService.ICreateUsersService;
import com.challenge.simpleApi.domains.users.services.DeleteUsersService.IDeleteUsersService;
import com.challenge.simpleApi.domains.users.services.UpdateUsersService.IUpdateUsersService;
import com.challenge.simpleApi.domains.users.services.getAllUsersService.IGetAllUsersService;
import com.challenge.simpleApi.domains.users.services.getUserByIdService.IGetUserByIdService;
import com.challenge.simpleApi.domains.users.services.getUserByUsernameService.IGetUserByUsernameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsersService implements UserDetailsService {

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Autowired
  private IGetAllUsersService getAllUsers;

  @Autowired
  private IGetUserByIdService getUserByIdService;

  @Autowired
  private ICreateUsersService createUsersService;

  @Autowired
  private IDeleteUsersService deleteUsersService;

  @Autowired
  private IUpdateUsersService updateUsersService;
  
  @Autowired
  private IGetUserByUsernameService getUserByUsernameService;
  
  @Autowired
  private IAuthenticateUserService authenticateUserService;

  public List<Users> GetAllUsers() {
    return this.getAllUsers.execute();
  }

  public Users GetUsersById(Long Id) {
    return this.getUserByIdService.execute(Id);
  }

  public Users CreateUser(Users user) {
    return this.createUsersService.execute(user);
  }

  public void DeleteUsers(Long Id) {
    Users user = this.GetUsersById(Id);
    deleteUsersService.execute(user);
  }

  public Users UpdateUsers(Users user, Long Id) {
    Users userExists = this.GetUsersById(Id);
    return this.updateUsersService.execute(user, Id);
  }
  
  public Users getUserByUsername(String username){
    return this.getUserByUsernameService.execute(username);
  }

  public JWTTokenDTO authenticateService(AuthenticationDTO userToAuthenticate){
    UserDetails usuario = this.loadUserByUsername(userToAuthenticate.getUsername());
    return  authenticateUserService.execute(usuario,userToAuthenticate.getPassword());
  }
  
  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    
    Users user = this.getUserByUsername(username);


    String[] roles = user.isAdmin()
      ? new String[]{"ADMIN","USER"}
      : new String[]{"USER"};

    return User.builder()
      .username(user.getUsername())
      .password(user.getPassword())
      .roles(roles)
      .build();
  }
}
