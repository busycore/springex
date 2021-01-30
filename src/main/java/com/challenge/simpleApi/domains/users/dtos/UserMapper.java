package com.challenge.simpleApi.domains.users.dtos;

import com.challenge.simpleApi.domains.users.models.Users;

import java.util.List;
import java.util.Map;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
@Mapper
public interface UserMapper {
  UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

  UserDTO usersToUserDTO(Users users);

  Users userDTOToUsers(UserDTO users);
  
  List<UserDTO> userListToUserDTO(List<Users> users);
}
