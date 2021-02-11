package com.challenge.simpleApi.domains.users.services.getUserByIdService;

import com.challenge.simpleApi.domains.users.models.Users;
import com.challenge.simpleApi.domains.users.repositories.UsersRepository;
import com.challenge.simpleApi.shared.errors.exceptions.NotFoundException;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Primary
public class GetUserByIdService implements IGetUserByIdService{

    private UsersRepository usersRepository;

    public GetUserByIdService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Override
    public Users execute(Long Id) {
        Optional<Users> foundUser = this.usersRepository.findById(Id);
        if(foundUser.isEmpty()){
            throw new NotFoundException("This user_id does not exists");
        }
        return foundUser.get();
    }
}
