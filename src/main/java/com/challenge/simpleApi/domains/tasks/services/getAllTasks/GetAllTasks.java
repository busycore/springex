package com.challenge.simpleApi.domains.tasks.services.getAllTasks;

import com.challenge.simpleApi.domains.tasks.models.Tasks;
import com.challenge.simpleApi.domains.tasks.repositories.TasksRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetAllTasks implements IGetAllTasks{

  @Autowired
  private TasksRepositories tasksRepositories;
  
  @Override
  public List<Tasks> execute() {
    return tasksRepositories.findAll();
  }
}
