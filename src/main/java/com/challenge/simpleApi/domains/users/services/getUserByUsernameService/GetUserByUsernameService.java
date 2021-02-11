package com.challenge.simpleApi.domains.users.services.getUserByUsernameService;

import com.challenge.simpleApi.domains.users.models.Users;
import com.challenge.simpleApi.domains.users.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Primary
public class GetUserByUsernameService implements IGetUserByUsernameService {

  @Autowired
  private UsersRepository usersRepository;
  
  @Override
  public Users execute(String username) {
    Optional<Users> foundUser = this.usersRepository.findByusername(username);
    
    if(foundUser.isEmpty()){
      throw new UsernameNotFoundException("This user is not found");
    }
    
    return foundUser.get();
  }
}
