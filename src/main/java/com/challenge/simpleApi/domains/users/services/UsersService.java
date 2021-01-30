package com.challenge.simpleApi.domains.users.services;

import com.challenge.simpleApi.domains.users.models.Users;
import com.challenge.simpleApi.domains.users.services.CreateUsersService.ICreateUsersService;
import com.challenge.simpleApi.domains.users.services.DeleteUsersService.IDeleteUsersService;
import com.challenge.simpleApi.domains.users.services.UpdateUsersService.IUpdateUsersService;
import com.challenge.simpleApi.domains.users.services.getAllUsersService.IGetAllUsersService;
import com.challenge.simpleApi.domains.users.services.getUserByIdService.IGetUserByIdService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsersService {

  @Autowired
  private IGetAllUsersService getAllUsers;

  @Autowired
  private IGetUserByIdService getUserByIdService;

  @Autowired
  private ICreateUsersService createUsersService;

  @Autowired
  private IDeleteUsersService deleteUsersService;

  @Autowired
  private IUpdateUsersService updateUsersService;

  public List<Users> GetAllUsers() {
    return this.getAllUsers.execute();
  }

  public Users GetUsersById(Long Id) {
    return this.getUserByIdService.execute(Id);
  }

  public Users CreateUser(Users user) {
    return this.createUsersService.execute(user);
  }

  public void DeleteUsers(Long Id) {
    Users user = this.GetUsersById(Id);
    deleteUsersService.execute(user);
  }

  public Users UpdateUsers(Users user, Long Id) {
    Users userExists = this.GetUsersById(Id);
    return this.updateUsersService.execute(user, Id);
  }
}
