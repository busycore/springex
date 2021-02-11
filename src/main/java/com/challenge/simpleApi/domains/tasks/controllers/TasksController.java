package com.challenge.simpleApi.domains.tasks.controllers;

import com.challenge.simpleApi.domains.tasks.dtos.TasksDTO;
import com.challenge.simpleApi.domains.tasks.dtos.TasksMapper;
import com.challenge.simpleApi.domains.tasks.models.Tasks;
import com.challenge.simpleApi.domains.tasks.services.TasksServices;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@RestController
@RequestMapping("tasks")
@Api(value = "Tasks Endpoint")
public class TasksController {

  @Autowired
  TasksServices tasksServices;

  @ApiOperation(value = "Get All tasks")
  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public List<TasksDTO> getAllTasks() {
    final List<Tasks> Tasks = this.tasksServices.getAllTasks();
    return TasksMapper.INSTANCE.tasksListToTasksDTO(Tasks);
  }

  @ApiOperation(value = "Get all tasks by an specific user")
  @GetMapping("/users/{id}")
  @ResponseStatus(HttpStatus.OK)
  public List<TasksDTO> getAllTasksFromUser(@PathVariable("id") Long id) {
    final List<Tasks> Tasks = this.tasksServices.getTasksByUserId(id);
    return TasksMapper.INSTANCE.tasksListToTasksDTO(Tasks);
  }

  @ApiOperation(value = "Get a specific task")
  @GetMapping("{id}")
  @ResponseStatus(HttpStatus.OK)
  public TasksDTO getSpecificTask(@PathVariable("id") Long id) {
    Tasks task = this.tasksServices.getGetTaskById(id);
    return TasksMapper.INSTANCE.tasksToTasksDTO(task);
  }

  @ApiOperation(value = "Create a task")
  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public TasksDTO createTask(@RequestBody TasksDTO task) {
    Tasks dtotask = TasksMapper.INSTANCE.tasksDTOToTasks(task);
    Tasks createdTask = this.tasksServices.createTask(dtotask);
    return TasksMapper.INSTANCE.tasksToTasksDTO(createdTask);
  }

  @ApiOperation(value = "A simple file uploader")
  @PostMapping("upload")
  @ResponseStatus(HttpStatus.OK)
  public String uploadTask(
    @RequestParam("file") MultipartFile file,
    RedirectAttributes redirectAttributes
  ) {
    return this.tasksServices.uploadFile(file);
  }
  
  

  @GetMapping("test")
  @ResponseStatus(HttpStatus.ACCEPTED)
  public String testMethod() {
    return "oi";
  }
}
