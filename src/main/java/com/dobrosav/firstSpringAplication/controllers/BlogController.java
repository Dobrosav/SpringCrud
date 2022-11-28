package com.dobrosav.firstSpringAplication.controllers;

import com.dobrosav.firstSpringAplication.models.Blog;
import com.dobrosav.firstSpringAplication.models.BlogReprository;
import com.dobrosav.firstSpringAplication.utill.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

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
    @ResponseBody
    public ResponseEntity getBlogById(@PathVariable String id){
        try {
            int blogId = Integer.parseInt(id);
            return new ResponseEntity(this.blogReprository.findById(blogId).get(), HttpStatus.OK);
        }
        catch (NoSuchElementException exception){
            message.setText("Not found");
            return new ResponseEntity(message, HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/blogWithId")
    @ResponseBody
    public ResponseEntity getBlogByIdFromParameters(@RequestParam(value = "id") int id){
        try {
            return new ResponseEntity(this.blogReprository.findById(id).get(), HttpStatus.OK);
        }
        catch (NoSuchElementException exception){
            message.setText("Not found");
            return new ResponseEntity(message, HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("/blog/{id}")
    @ResponseBody
    public ResponseEntity deleteById(@PathVariable String id){
        try {
            int blogId = Integer.parseInt(id);
            this.blogReprository.deleteById(blogId);
            message.setText("Blog deleted");
            return new ResponseEntity(message, HttpStatus.OK);
        }
        catch (EmptyResultDataAccessException exception){
            message.setText("Not found");
            return new ResponseEntity(message, HttpStatus.NOT_FOUND);
        }

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
