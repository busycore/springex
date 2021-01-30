package com.challenge.simpleApi.domains.users.services.getUserByIdService;

import com.challenge.simpleApi.domains.users.models.Users;


public interface IGetUserByIdService {
    public Users execute(Long Id);
}
