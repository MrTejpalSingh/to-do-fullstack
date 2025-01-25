package com.fullstack.to_do_app.task.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "tasks")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Tasks {
    @Id
    @Column(name = "task_id")
    private String taskId;

    @Column(name = "title", nullable = false, length = 100)
    private String title;

    @Column(name = "description", length = 500)
    private String description;

    @Column(name = "due_date")
    private LocalDate dueDate;

    @Column(name = "priority", length = 50)
    private String priority;

    @Column(name = "status", length = 50)
    private String status;

    @Column(name = "username", nullable = false, length = 50)
    private String username;

    @Column(name = "category_id")
    private String categoryId;

}
