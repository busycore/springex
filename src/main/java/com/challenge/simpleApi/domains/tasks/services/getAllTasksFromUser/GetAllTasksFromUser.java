package com.challenge.simpleApi.domains.tasks.services.getAllTasksFromUser;

import com.challenge.simpleApi.domains.tasks.models.Tasks;
import com.challenge.simpleApi.domains.tasks.repositories.TasksRepositories;
import com.challenge.simpleApi.domains.users.models.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetAllTasksFromUser implements IGetAllTasksFromUser{
  
  @Autowired
  private TasksRepositories tasksRepositories;
  
  @Override
  public List<Tasks> execute(Users users) {
    return tasksRepositories.findByUserId(users.getId());
  }
}
