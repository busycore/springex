package com.challenge.simpleApi.domains.users.services.getAllUsersService;

import com.challenge.simpleApi.domains.users.models.Users;
import com.challenge.simpleApi.domains.users.repositories.UsersRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Primary
public class GetAllUsersService implements IGetAllUsersService {

    public UsersRepository usersRepository;

    public GetAllUsersService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Override
    public List<Users> execute() {
        return this.usersRepository.findAll();
    }
}
