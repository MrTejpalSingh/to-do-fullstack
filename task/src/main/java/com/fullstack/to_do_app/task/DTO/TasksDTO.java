package com.fullstack.to_do_app.task.DTO;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TasksDTO {
    private String taskId;
    private String title;
    private String description;
    private LocalDate dueDate;
    private String priority;
    private String status;
    private String username;
    private String categoryId;
}
