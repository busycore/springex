package com.challenge.simpleApi.domains.tasks.services.createTaskService;

import com.challenge.simpleApi.domains.tasks.models.Tasks;

public interface ICreateTaskService {
  public Tasks execute(Tasks task);
}
