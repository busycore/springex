package com.challenge.simpleApi.domains.tasks.services.createTaskService;

import com.challenge.simpleApi.domains.tasks.models.Tasks;
import com.challenge.simpleApi.domains.tasks.repositories.TasksRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateTaskService implements ICreateTaskService{
  @Autowired
  private TasksRepositories tasksRepositories;
  
  @Override
  public Tasks execute(Tasks task) {
    return tasksRepositories.save(task);
  }
}
