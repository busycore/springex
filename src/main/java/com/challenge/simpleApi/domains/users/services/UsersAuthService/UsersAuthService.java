//package com.challenge.simpleApi.domains.users.services.UsersAuthService;
//
//import com.challenge.simpleApi.domains.users.models.Users;
//import com.challenge.simpleApi.domains.users.services.getUserByUsernameService.IGetUserByUsernameService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//
//@Service
//public class UsersAuthService implements UserDetailsService {
//
//  @Autowired
//  private PasswordEncoder passwordEncoder;
//  
//  @Autowired
//  private IGetUserByUsernameService getUserByUsernameService;
//  
//  @Override
//  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//    
//    if(!username.equals("fulano")){
//      throw new UsernameNotFoundException("Nao encontrado");
//    }
//
//
//    Users user = getUserByUsernameService.execute(username);
//    
//    String[] roles = user.isAdmin() 
//      ? new String[]{"ADMIN","USER"}
//      : new String[]{"USER"};
//
//    return User.builder()
//      .username(user.getUsername())
//      .password(user.getPassword())
//      .roles("USER","ADMIN")
//      .build();
//  }
//}
