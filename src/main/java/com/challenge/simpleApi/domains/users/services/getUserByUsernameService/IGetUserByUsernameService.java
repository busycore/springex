package com.challenge.simpleApi.domains.users.services.getUserByUsernameService;

import com.challenge.simpleApi.domains.users.models.Users;

public interface IGetUserByUsernameService {
  public Users execute(String username);
}
