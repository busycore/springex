package com.challenge.simpleApi.domains.users.services.CreateUsersService;

import com.challenge.simpleApi.domains.users.models.Users;
import com.challenge.simpleApi.domains.users.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Primary
public class CreateUsersService implements ICreateUsersService{

  
    @Autowired
    private PasswordEncoder passwordEncoder;
  
    private UsersRepository usersRepository;
    

    public CreateUsersService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository; 
    }
    
    @Override
    public Users execute(Users user) {
        System.out.println(user);
        String encodedPass = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPass);
        System.out.println(encodedPass);
        return this.usersRepository.save(user);
    }
}
