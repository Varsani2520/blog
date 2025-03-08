package com.blog.blog.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.blog.Entities.UserDTO;
import com.blog.blog.payload.ApiResponse;
import com.blog.blog.services.UserService;

@RestController
@RequestMapping("/api/users")

public class userControllers {
    @Autowired
    private UserService uService;

    @PostMapping
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userdto) {
        uService.createUser(userdto);
        return new ResponseEntity<>(userdto, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUser() {
        return new ResponseEntity<>(this.uService.getAllUSer(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable int id) {
        UserDTO User = this.uService.getUserById(id);
        return new ResponseEntity<>(User, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable int id, @RequestBody UserDTO udto) {
        UserDTO user = this.uService.updateUser(udto, id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteUserById(@PathVariable int id) {
        this.uService.deleteUser(id);
        return new ResponseEntity<ApiResponse>(new ApiResponse("user deleted sucess", true), HttpStatus.OK);
    }
}
