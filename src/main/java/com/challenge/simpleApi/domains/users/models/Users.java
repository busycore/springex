package com.challenge.simpleApi.domains.users.models;

import com.challenge.simpleApi.domains.tasks.models.Tasks;
import java.util.Set;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class Users {
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "name", nullable = false)
  private String name;

  @Column(name = "age", nullable = false)
  private Integer age;

  @OneToMany(mappedBy = "user_id")
  private Set<Tasks> tasks;
}
