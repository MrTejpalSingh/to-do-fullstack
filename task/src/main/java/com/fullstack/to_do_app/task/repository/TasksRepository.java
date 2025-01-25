package com.fullstack.to_do_app.task.repository;

import com.fullstack.to_do_app.task.entity.Tasks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TasksRepository extends JpaRepository<Tasks,String> {
}
