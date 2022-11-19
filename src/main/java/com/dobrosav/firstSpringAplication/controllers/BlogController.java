package com.dobrosav.firstSpringAplication.controllers;

import com.dobrosav.firstSpringAplication.models.Blog;
import com.dobrosav.firstSpringAplication.models.BlogReprository;
import com.dobrosav.firstSpringAplication.utill.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BlogController {
    @Autowired
    BlogReprository blogReprository;
    private static Message message = new Message();
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
    public ResponseEntity deleteById(@PathVariable String id){
        int blogId = Integer.parseInt(id);
        this.blogReprository.deleteById(blogId);
        message.setText("Blog deleted");
        return new ResponseEntity(message, HttpStatus.OK);
    }
    @PostMapping("/blog")
    @ResponseBody
    public ResponseEntity create(@RequestBody Blog body){
        String title=body.getTitle();
        String content=body.getContent();
        this.blogReprository.save(new Blog(title, content));
        message.setText("Blog added");
        return new ResponseEntity(message, HttpStatus.OK);
    }


}
