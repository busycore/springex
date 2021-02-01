package com.challenge.simpleApi.domains.tasks.services.getAllTasksFromUser;

import com.challenge.simpleApi.domains.tasks.models.Tasks;
import com.challenge.simpleApi.domains.users.models.Users;

import java.util.List;

public interface IGetAllTasksFromUser {
  List<Tasks> execute(Users users);
}
