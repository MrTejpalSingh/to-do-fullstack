package com.fullstack.to_do_app.task.service;

import com.fullstack.to_do_app.task.DTO.ResponseDTO;
import com.fullstack.to_do_app.task.DTO.TasksDTO;
import com.fullstack.to_do_app.task.entity.Tasks;
import com.fullstack.to_do_app.task.repository.TasksRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.fullstack.to_do_app.task.util.TaskServiceConstants.*;

@Slf4j
@Service
public class TasksServiceImpl implements TasksService {

    @Autowired
    TasksRepository tasksRepository;

    @Override
    public TasksDTO getTaskById(String id) {
        log.info("Fetching task with ID: {}", id);
        return tasksRepository.findById(id)
                .map(task -> new TasksDTO(
                        task.getTaskId(),
                        task.getTitle(),
                        task.getDescription(),
                        task.getDueDate(),
                        task.getPriority(),
                        task.getStatus(),
                        task.getUsername(),
                        task.getCategoryId()
                ))
                .orElseThrow(() -> {
                    log.error("Task not found with ID: {}", id);
                    return new RuntimeException(ADD_TASK_FAILURE + id);
                });
    }

    @Override
    public List<TasksDTO> getAllTasks() {
        log.info("Fetching all tasks");
        List<TasksDTO> tasks = tasksRepository.findAll()
                .stream()
                .map(task -> new TasksDTO(
                        task.getTaskId(),
                        task.getTitle(),
                        task.getDescription(),
                        task.getDueDate(),
                        task.getPriority(),
                        task.getStatus(),
                        task.getUsername(),
                        task.getCategoryId()
                ))
                .toList();
        log.info("Successfully fetched all tasks");
        return tasks;
    }

    @Override
    public ResponseDTO addTask(TasksDTO tasksDTO) {
        log.info("Adding a single task: {}", tasksDTO);
        Tasks task = new Tasks();
        task.setTaskId(tasksDTO.getTaskId());
        task.setTitle(tasksDTO.getTitle());
        task.setDescription(tasksDTO.getDescription());
        task.setDueDate(tasksDTO.getDueDate());
        task.setPriority(tasksDTO.getPriority());
        task.setStatus(tasksDTO.getStatus());
        task.setUsername(tasksDTO.getUsername());
        task.setCategoryId(tasksDTO.getCategoryId());

        tasksRepository.save(task);
        log.info("Successfully added task: {}", tasksDTO);
        return new ResponseDTO(STATUS_SUCCESS, ADD_TASK, ADD_TASK_SUCCESS);
    }

    @Override
    public ResponseDTO addAllTasks(List<TasksDTO> tasksDTOS) {
        log.info("Adding {} tasks in bulk", tasksDTOS.size());
        List<Tasks> tasks = tasksDTOS.stream().map(dto -> {
            Tasks task = new Tasks();
            task.setTaskId(dto.getTaskId());
            task.setTitle(dto.getTitle());
            task.setDescription(dto.getDescription());
            task.setDueDate(dto.getDueDate());
            task.setPriority(dto.getPriority());
            task.setStatus(dto.getStatus());
            task.setUsername(dto.getUsername());
            task.setCategoryId(dto.getCategoryId());
            return task;
        }).toList();

        tasksRepository.saveAll(tasks);
        log.info("Successfully added {} tasks", tasksDTOS.size());
        return new ResponseDTO(STATUS_SUCCESS, ADD_ALL_TASKS, ADD_ALL_TASKS_SUCCESS);
    }

    @Override
    public ResponseDTO updateTask(TasksDTO tasksDTO) {
        log.info("Updating task with ID: {}", tasksDTO.getTaskId());
        Tasks task = tasksRepository.findById(tasksDTO.getTaskId())
                .orElseThrow(() -> {
                    log.error("Task not found with ID: {}", tasksDTO.getTaskId());
                    return new RuntimeException(UPDATE_TASK_FAILURE + tasksDTO.getTaskId());
                });

        task.setTitle(tasksDTO.getTitle());
        task.setDescription(tasksDTO.getDescription());
        task.setDueDate(tasksDTO.getDueDate());
        task.setPriority(tasksDTO.getPriority());
        task.setStatus(tasksDTO.getStatus());
        task.setUsername(tasksDTO.getUsername());
        task.setCategoryId(tasksDTO.getCategoryId());

        tasksRepository.save(task);
        log.info("Successfully updated task with ID: {}", tasksDTO.getTaskId());
        return new ResponseDTO(STATUS_SUCCESS, UPDATE_TASK, UPDATE_TASK_SUCCESS);
    }

    @Override
    public ResponseDTO deleteTaskById(String id) {
        log.info("Deleting task with ID: {}", id);
        Tasks task = tasksRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Task not found with ID: {}", id);
                    return new RuntimeException(DELETE_TASK_BY_ID_FAILURE + id);
                });

        tasksRepository.delete(task);
        log.info("Successfully deleted task with ID: {}", id);
        return new ResponseDTO(STATUS_SUCCESS, DELETE_TASK_BY_ID, DELETE_TASK_BY_ID_SUCCESS);
    }

    @Override
    public ResponseDTO deleteAllTasks() {
        log.info("Deleting all tasks");
        tasksRepository.deleteAll();
        log.info("Successfully deleted all tasks");
        return new ResponseDTO(STATUS_SUCCESS, DELETE_ALL_TASKS, DELETE_ALL_TASKS_SUCCESS);
    }
}
