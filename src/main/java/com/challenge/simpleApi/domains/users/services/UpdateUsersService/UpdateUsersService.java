package com.challenge.simpleApi.domains.users.services.UpdateUsersService;

import com.challenge.simpleApi.domains.users.models.Users;
import com.challenge.simpleApi.domains.users.repositories.UsersRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@Primary
public class UpdateUsersService implements IUpdateUsersService {

  private UsersRepository usersRepository;

  public UpdateUsersService(UsersRepository usersRepository) {
    this.usersRepository = usersRepository;
  }

  @Override
  public Users execute(Users user, Long Id) {
    user.setId(Id);
    return this.usersRepository.save(user);
  }
}
