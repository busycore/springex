package com.challenge.simpleApi.domains.tasks.services.getTaskById;

import com.challenge.simpleApi.domains.tasks.models.Tasks;

public interface IGetTaskById {
  public Tasks execute(Long Id);
}
