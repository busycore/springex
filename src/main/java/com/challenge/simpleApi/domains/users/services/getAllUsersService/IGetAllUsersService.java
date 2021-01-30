package com.challenge.simpleApi.domains.users.services.getAllUsersService;

import com.challenge.simpleApi.domains.users.models.Users;

import java.util.List;

public interface IGetAllUsersService {
    public List<Users> execute();
}
