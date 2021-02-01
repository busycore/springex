package com.challenge.simpleApi.domains.users.dtos;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsersDTO {

  @ApiModelProperty(notes = "Id of the User",name="Id",required=true,value="1")
  private Long id;
  
  @ApiModelProperty(notes = "Name of the User",name="name",required=true,value="John Doe")
  private String name;

  @ApiModelProperty(notes = "User Age",name="age",required=true,value="23")
  private Integer age;
}
