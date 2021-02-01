package com.challenge.simpleApi.domains.tasks.services.getAllTasks;

import com.challenge.simpleApi.domains.tasks.models.Tasks;

import java.util.List;

public interface IGetAllTasks {
  public List<Tasks> execute();
}
