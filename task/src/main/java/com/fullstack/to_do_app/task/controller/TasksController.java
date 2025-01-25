package com.fullstack.to_do_app.task.controller;

import com.fullstack.to_do_app.task.DTO.ResponseDTO;
import com.fullstack.to_do_app.task.DTO.TasksDTO;
import com.fullstack.to_do_app.task.service.TasksService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(path = "/tasks")
public class TasksController {

    @Autowired
    TasksService tasksService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<TasksDTO> getTask(@PathVariable("id") String id) {
        log.info("Fetching task with ID: {}", id);
        TasksDTO task = tasksService.getTaskById(id);
        log.info("Successfully fetched task with ID: {}", id);
        return ResponseEntity.ok(task);
    }

    @GetMapping
    public ResponseEntity<List<TasksDTO>> getAllTasks() {
        log.info("Fetching all tasks");
        List<TasksDTO> tasks = tasksService.getAllTasks();
        log.info("Successfully fetched all tasks");
        return ResponseEntity.ok(tasks);
    }

    @PostMapping
    public ResponseEntity<ResponseDTO> addTask(@RequestBody TasksDTO tasksDTO) {
        log.info("Received request to add a single task");
        ResponseDTO response = tasksService.addTask(tasksDTO);
        log.info("Successfully added a task");
        return ResponseEntity.ok(response);
    }

    @PostMapping(value = "/bulk")
    public ResponseEntity<ResponseDTO> addAllTasks(@RequestBody List<TasksDTO> tasksDTOS) {
        log.info("Received request to add {} tasks", tasksDTOS.size());
        ResponseDTO response = tasksService.addAllTasks(tasksDTOS);
        log.info("Successfully added {} tasks", tasksDTOS.size());
        return ResponseEntity.ok(response);
    }

    @PutMapping(value = "/modify")
    public ResponseEntity<ResponseDTO> updateTask(@RequestBody TasksDTO tasksDTO) {
        log.info("Received request to update task with ID: {}", tasksDTO.getTaskId());
        ResponseDTO response = tasksService.updateTask(tasksDTO);
        log.info("Successfully updated task with ID: {}", tasksDTO.getTaskId());
        return ResponseEntity.ok(response);
    }

    @DeleteMapping(value = "delete/{id}")
    public ResponseEntity<ResponseDTO> deleteTaskById(@PathVariable("id") String id) {
        log.info("Received request to delete task with ID: {}", id);
        ResponseDTO response = tasksService.deleteTaskById(id);
        log.info("Successfully deleted task with ID: {}", id);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping(value = "delete")
    public ResponseEntity<ResponseDTO> deleteAllTasks() {
        log.info("Received request to delete all tasks");
        ResponseDTO response = tasksService.deleteAllTasks();
        log.info("Successfully deleted all tasks");
        return ResponseEntity.ok(response);
    }
}
