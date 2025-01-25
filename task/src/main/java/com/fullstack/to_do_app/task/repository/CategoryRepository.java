package com.fullstack.to_do_app.task.repository;

import com.fullstack.to_do_app.task.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category,String> {
}
