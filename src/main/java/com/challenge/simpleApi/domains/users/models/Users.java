package com.challenge.simpleApi.domains.users.models;

import com.challenge.simpleApi.domains.tasks.models.Tasks;
import com.challenge.simpleApi.shared.validations.NotMinor.NotMinor;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class Users {


  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "name", nullable = false)
  @NotEmpty(message = "Name should not be empty")
  private String name;
  
  @Column
  @NotEmpty(message = "Username should not be empty")
  private String username;
  
  @Column
  @NotNull(message = "Username should not be empty")
  private String password;
  
  @Column
  @NotNull(message = "admin should not be empty")
  private boolean admin;

  @Column(name = "age", nullable = false)
  @NotNull(message = "Age should not be empty")
  @NotMinor(message = "Should not be less than 18")
  private Integer age;
  
  @OneToMany(mappedBy = "user")
  private List<Tasks> tasks;
}
