package com.challenge.simpleApi.domains.tasks.dtos;

import com.challenge.simpleApi.domains.tasks.models.Tasks;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface TasksMapper {
  TasksMapper INSTANCE = Mappers.getMapper(TasksMapper.class);
  
  @Mapping(source = "user.name",target = "owner")
  TasksDTO tasksToTasksDTO(Tasks task);
  
  List<TasksDTO> tasksListToTasksDTO(List<Tasks> task);
  
  Tasks tasksDTOToTasks(TasksDTO task);
}
