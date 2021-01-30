package com.challenge.simpleApi.domains.users.controllers;

import com.challenge.simpleApi.domains.users.dtos.UserDTO;
import com.challenge.simpleApi.domains.users.dtos.UserMapper;
import com.challenge.simpleApi.domains.users.models.Users;

import com.challenge.simpleApi.domains.users.services.UsersService;
import java.util.List;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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
  public List<UserDTO> GetAllUsers() {

    List<UserDTO> users = UserMapper.INSTANCE.userListToUserDTO(usersService.GetAllUsers());
    return users;
  }

  @ApiOperation(value = "Get an user by id")
  @GetMapping("{id}")
  @ResponseStatus(HttpStatus.OK)
  public UserDTO GetSpecificUser(@PathVariable("id") Long id) {
    UserDTO user = UserMapper.INSTANCE.usersToUserDTO(this.usersService.GetUsersById(id));
    return user;
  }

  @ApiOperation(value = "Create a new user")
  @ApiResponses(value ={
    @ApiResponse(code = 201,message = "An user was created",response = UserDTO.class),
    @ApiResponse(code = 400,message = "Your JSON is not correct")
  } )
  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public UserDTO CreateUser(@RequestBody UserDTO user) {
    logger.info("Received " + user);
    Users userdto = UserMapper.INSTANCE.userDTOToUsers(user);
    UserDTO dtouser =UserMapper.INSTANCE.usersToUserDTO(this.usersService.CreateUser(userdto));
    logger.info("Returned " + dtouser);
    return dtouser;
  }

  @ApiOperation(value = "Update an User")
  @PutMapping("{id}")
  @ResponseStatus(HttpStatus.OK)
  public UserDTO UpdateUser(
    @PathVariable("id") Long id,
    @RequestBody UserDTO user
  ) {
    
    Users usertdto = UserMapper.INSTANCE.userDTOToUsers(user);
    UserDTO dtouser =UserMapper.INSTANCE.usersToUserDTO(this.usersService.UpdateUsers(usertdto,id));  
    
    return dtouser;
  }

  @ApiOperation(value = "Delete an specific user")
  @DeleteMapping("{id}")
  @ResponseStatus(HttpStatus.OK)
  public void DeleteUser(@PathVariable("id") Long id) {
    this.usersService.DeleteUsers(id);
  }
}
