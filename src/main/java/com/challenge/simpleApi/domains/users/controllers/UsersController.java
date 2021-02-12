package com.challenge.simpleApi.domains.users.controllers;

import com.challenge.simpleApi.domains.users.dtos.AuthenticationDTO;
import com.challenge.simpleApi.domains.users.dtos.JWTTokenDTO;
import com.challenge.simpleApi.domains.users.dtos.UsersDTO;
import com.challenge.simpleApi.domains.users.dtos.UsersMapper;
import com.challenge.simpleApi.domains.users.models.Users;

import com.challenge.simpleApi.domains.users.services.UsersService;

import java.util.List;

import com.challenge.simpleApi.shared.http.APIResponse;
import com.challenge.simpleApi.shared.security.JWT.JWTService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
  //public APIResponse<List<UsersDTO>> GetAllUsers(HttpServletRequest request, HttpServletResponse response) {
  public APIResponse<List<UsersDTO>> GetAllUsers() {
    List<UsersDTO> users = UsersMapper.INSTANCE.userListToUserDTO(usersService.GetAllUsers());

    return new APIResponse<List<UsersDTO>>(users,HttpStatus.OK);
  }

  @ApiOperation(value = "Get an user by id")
  @GetMapping("{id}")
  @ResponseStatus(HttpStatus.OK)
  public APIResponse<UsersDTO> GetSpecificUser(@PathVariable("id") Long id) {
    UsersDTO user = UsersMapper.INSTANCE.usersToUserDTO(this.usersService.GetUsersById(id));
    return new APIResponse<UsersDTO>(user,HttpStatus.OK);
  }

  @ApiOperation(value = "Create a new user")
  @ApiResponses(value = {
    @ApiResponse(code = 201, message = "An user was created", response = UsersDTO.class),
    @ApiResponse(code = 400, message = "Your JSON is not correct")
  })
  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public APIResponse<UsersDTO> CreateUser(@RequestBody UsersDTO user) {
    logger.info("Received " + user);
    Users userdto = UsersMapper.INSTANCE.userDTOToUsers(user);
    UsersDTO dtouser = UsersMapper.INSTANCE.usersToUserDTO(this.usersService.CreateUser(userdto));

    logger.info("Returned " + dtouser);
    return new APIResponse<UsersDTO>(dtouser,HttpStatus.CREATED);
  }

  @ApiOperation(value = "Update an User")
  @PutMapping("{id}")
  @ResponseStatus(HttpStatus.OK)
  public APIResponse<UsersDTO> UpdateUser(
    @PathVariable("id") Long id,
    @RequestBody UsersDTO user
  ) {

    Users usertdto = UsersMapper.INSTANCE.userDTOToUsers(user);
    UsersDTO dtouser = UsersMapper.INSTANCE.usersToUserDTO(this.usersService.UpdateUsers(usertdto, id));

    return new APIResponse<UsersDTO>(dtouser,HttpStatus.OK);
  }

  @PostMapping("/authenticate")
  @ResponseStatus(HttpStatus.OK)
  public APIResponse<JWTTokenDTO> authenticate(@RequestBody AuthenticationDTO credentials) {
    JWTTokenDTO authenticatedUser = this.usersService.authenticateService(credentials);
    return new APIResponse<JWTTokenDTO>(authenticatedUser,HttpStatus.OK);
  }
  
  @ApiOperation(value = "Delete an specific user")
  @DeleteMapping("{id}")
  @ResponseStatus(HttpStatus.OK)
  public void DeleteUser(@PathVariable("id") Long id) {
    this.usersService.DeleteUsers(id);
  }
}
