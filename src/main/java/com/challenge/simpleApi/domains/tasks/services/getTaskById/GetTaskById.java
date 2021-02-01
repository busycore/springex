package com.challenge.simpleApi.domains.tasks.services.getTaskById;

import com.challenge.simpleApi.domains.tasks.models.Tasks;
import com.challenge.simpleApi.domains.tasks.repositories.TasksRepositories;
import com.challenge.simpleApi.shared.errors.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GetTaskById implements IGetTaskById{
@Autowired
  private TasksRepositories tasksRepositories;
  
  @Override
  public Tasks execute(Long Id) {
    Optional<Tasks> foundTask = tasksRepositories.findById(Id);
    if(foundTask.isEmpty()){
      throw new NotFoundException("Task not found");
    }

    return foundTask.get();
  }
}
