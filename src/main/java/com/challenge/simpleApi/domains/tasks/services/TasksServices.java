package com.challenge.simpleApi.domains.tasks.services;

import com.challenge.simpleApi.domains.tasks.models.Tasks;
import com.challenge.simpleApi.domains.tasks.services.createTaskService.CreateTaskService;
import com.challenge.simpleApi.domains.tasks.services.createTaskService.ICreateTaskService;
import com.challenge.simpleApi.domains.tasks.services.getAllTasks.GetAllTasks;
import com.challenge.simpleApi.domains.tasks.services.getAllTasks.IGetAllTasks;
import com.challenge.simpleApi.domains.tasks.services.getTaskById.GetTaskById;
import com.challenge.simpleApi.domains.tasks.services.getTaskById.IGetTaskById;
import com.challenge.simpleApi.domains.tasks.services.uploadTaskService.IUploadTaskService;
import com.challenge.simpleApi.domains.tasks.services.uploadTaskService.UploadTaskService;
import com.challenge.simpleApi.domains.users.models.Users;
import com.challenge.simpleApi.domains.users.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class TasksServices {
  
  @Autowired
  private IGetAllTasks getAllTasks;
  
  @Autowired
  private IGetTaskById getTaskById;
  
  @Autowired
  private ICreateTaskService createTaskService;
  
  @Autowired
  private UsersService usersService;
  
  @Autowired
  private IUploadTaskService uploadTaskService;
  
  public List<Tasks> getAllTasks(){
    return this.getAllTasks.execute();
  }

  public Tasks getGetTaskById(Long Id) {
    return this.getTaskById.execute(Id);
  }
  
  public Tasks createTask(Tasks task){
    return this.createTaskService.execute(task);
  }
  
  public List<Tasks> getTasksByUserId(Long Id){
    Users user = this.usersService.GetUsersById(Id);
    return this.getTasksByUserId(user.getId());
  }
  
  public String uploadFile(MultipartFile file){
    return this.uploadTaskService.execute(file);
  }
}
