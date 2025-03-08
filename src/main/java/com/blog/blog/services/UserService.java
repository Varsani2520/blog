package com.blog.blog.services;

import java.util.List;
import com.blog.blog.Entities.UserDTO;



public interface UserService {
    List<UserDTO> getAllUSer();

    UserDTO getUserById(Integer id);

    // create user
    UserDTO createUser(UserDTO User);

    // update user id
    UserDTO updateUser(UserDTO User, Integer id);

    void deleteUser(int id);
}
