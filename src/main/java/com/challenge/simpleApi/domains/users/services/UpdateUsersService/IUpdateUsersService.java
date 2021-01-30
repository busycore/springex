package com.challenge.simpleApi.domains.users.services.UpdateUsersService;

import com.challenge.simpleApi.domains.users.models.Users;

public interface IUpdateUsersService {
    public Users execute(Users user, Long Id);
}
