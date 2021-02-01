package com.challenge.simpleApi.domains.users.dtos;

import com.challenge.simpleApi.domains.users.models.Users;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
@Mapper
public interface UsersMapper {
  UsersMapper INSTANCE = Mappers.getMapper(UsersMapper.class);

  UsersDTO usersToUserDTO(Users users);

  Users userDTOToUsers(UsersDTO users);
  
  List<UsersDTO> userListToUserDTO(List<Users> users);
}
