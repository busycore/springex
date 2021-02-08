package com.challenge.simpleApi.domains.tasks.dtos;

import com.challenge.simpleApi.domains.users.models.Users;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TasksDTO {
  
  
    private Long id;

    private String name;

    private String status;
    
    private Long user_id;

    private String owner;
    
    private LocalDateTime due_date;

    private LocalDateTime finished_date;
  
}
