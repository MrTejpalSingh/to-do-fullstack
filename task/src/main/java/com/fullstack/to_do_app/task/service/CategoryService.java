package com.fullstack.to_do_app.task.service;

import com.fullstack.to_do_app.task.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {

    @Autowired
    CategoryRepository categoryRepository;


}
