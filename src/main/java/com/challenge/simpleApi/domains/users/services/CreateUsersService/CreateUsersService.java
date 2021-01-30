package com.challenge.simpleApi.domains.users.services.CreateUsersService;

import com.challenge.simpleApi.domains.users.models.Users;
import com.challenge.simpleApi.domains.users.repositories.UsersRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@Primary
public class CreateUsersService implements ICreateUsersService{

    private UsersRepository usersRepository;

    public CreateUsersService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Override
    public Users execute(Users user) {
        return this.usersRepository.save(user);
    }
}
