package com.challenge.simpleApi.domains.users.controllers;

import com.challenge.simpleApi.domains.users.dtos.AuthenticationDTO;
import com.challenge.simpleApi.domains.users.dtos.JWTTokenDTO;
import com.challenge.simpleApi.domains.users.dtos.UsersDTO;
import com.challenge.simpleApi.domains.users.dtos.UsersMapper;
import com.challenge.simpleApi.domains.users.models.Users;

import com.challenge.simpleApi.domains.users.services.UsersService;

import java.util.List;

import com.challenge.simpleApi.shared.security.JWT.JWTService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("users")
@Api(value = "Users Endpoint")

public class UsersController {

  Logger logger = LoggerFactory.getLogger(UsersController.class);

  private UsersService usersService;


  public UsersController(UsersService usersService) {
    this.usersService = usersService;
  }

  @ApiOperation(value = "Get All users")
  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public List<UsersDTO> GetAllUsers() {
    List<UsersDTO> users = UsersMapper.INSTANCE.userListToUserDTO(usersService.GetAllUsers());
    return users;
  }

  @ApiOperation(value = "Get an user by id")
  @GetMapping("{id}")
  @ResponseStatus(HttpStatus.OK)
  public UsersDTO GetSpecificUser(@PathVariable("id") Long id) {
    UsersDTO user = UsersMapper.INSTANCE.usersToUserDTO(this.usersService.GetUsersById(id));
    return user;
  }

  @ApiOperation(value = "Create a new user")
  @ApiResponses(value = {
    @ApiResponse(code = 201, message = "An user was created", response = UsersDTO.class),
    @ApiResponse(code = 400, message = "Your JSON is not correct")
  })
  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public UsersDTO CreateUser(@RequestBody UsersDTO user) {
    logger.info("Received " + user);
    Users userdto = UsersMapper.INSTANCE.userDTOToUsers(user);
    UsersDTO dtouser = UsersMapper.INSTANCE.usersToUserDTO(this.usersService.CreateUser(userdto));

    logger.info("Returned " + dtouser);
    return dtouser;
  }

  @ApiOperation(value = "Update an User")
  @PutMapping("{id}")
  @ResponseStatus(HttpStatus.OK)
  public UsersDTO UpdateUser(
    @PathVariable("id") Long id,
    @RequestBody UsersDTO user
  ) {

    Users usertdto = UsersMapper.INSTANCE.userDTOToUsers(user);
    UsersDTO dtouser = UsersMapper.INSTANCE.usersToUserDTO(this.usersService.UpdateUsers(usertdto, id));

    return dtouser;
  }

  @PostMapping("/authenticate")
  @ResponseStatus(HttpStatus.OK)
  public JWTTokenDTO authenticate(@RequestBody AuthenticationDTO credentials) {
    final Users user = Users.builder().username(credentials.getUsername()).password(credentials.getPassword()).build();
    JWTTokenDTO authenticatedUser = this.usersService.authenticateService(user);
    return authenticatedUser;
  }
  
  @ApiOperation(value = "Delete an specific user")
  @DeleteMapping("{id}")
  @ResponseStatus(HttpStatus.OK)
  public void DeleteUser(@PathVariable("id") Long id) {
    this.usersService.DeleteUsers(id);
  }
}
