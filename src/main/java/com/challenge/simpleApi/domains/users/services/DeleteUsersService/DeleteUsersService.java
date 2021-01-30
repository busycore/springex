package com.challenge.simpleApi.domains.users.services.DeleteUsersService;

import com.challenge.simpleApi.domains.users.models.Users;
import com.challenge.simpleApi.domains.users.repositories.UsersRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;


@Service
@Primary
public class DeleteUsersService implements IDeleteUsersService {

    private UsersRepository usersRepository;

    public DeleteUsersService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }


    public void execute(Users user) {
        this.usersRepository.delete(user);
    }


}
