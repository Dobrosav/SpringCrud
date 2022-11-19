package com.dobrosav.firstSpringAplication.controllers;

import com.dobrosav.firstSpringAplication.models.Blog;
import com.dobrosav.firstSpringAplication.models.BlogReprository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BlogController {
    @Autowired
    BlogReprository blogReprository;
    private int blogId;

    @GetMapping("/blog")
    public List<Blog> index(){
        return this.blogReprository.findAll();
    }
    @GetMapping("/blog/{id}")
    public Blog getBlogById(@PathVariable String id){
        int blogId = Integer.parseInt(id);
        return this.blogReprository.findById(blogId).get();
    }
    @DeleteMapping("/blog/{id}")
    public String deleteById(@PathVariable String id){
        int blogId = Integer.parseInt(id);
        this.blogReprository.deleteById(blogId);
        return "DELETED";
    }


}
