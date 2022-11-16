package com.dobrosav.firstSpringAplication.controllers;

import com.dobrosav.firstSpringAplication.models.Blog;
import com.dobrosav.firstSpringAplication.models.BlogReprository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BlogController {
    @Autowired
    BlogReprository blogReprository;

    @GetMapping("/blog")
    public List<Blog> index(){
        return this.blogReprository.findAll();
    }
}
