package com.blog.blog.services;

import java.util.List;

import com.blog.blog.payload.UserDTO;

public interface UserService {
    // all user
    List<UserDTO> getAllUSer();

    // user by id
    UserDTO getUserById(Integer id);

    // create user
    UserDTO createUser(UserDTO User);

    // update user 
    UserDTO updateUser(UserDTO User, Integer id);

    // delete user
    void deleteUser(int id);
}
