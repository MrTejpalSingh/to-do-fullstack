package com.fullstack.to_do_app.task.service;

import com.fullstack.to_do_app.task.DTO.ResponseDTO;
import com.fullstack.to_do_app.task.DTO.TasksDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface TasksService {

    TasksDTO getTaskById(String id);
    List<TasksDTO> getAllTasks();
    ResponseDTO addTask(TasksDTO tasksDTO);
    ResponseDTO addAllTasks(List<TasksDTO> tasksDTOS);
    ResponseDTO updateTask(TasksDTO tasksDTO);
    ResponseDTO deleteTaskById(String id);
    ResponseDTO deleteAllTasks();
}
