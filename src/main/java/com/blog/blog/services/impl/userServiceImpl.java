package com.blog.blog.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.blog.Entities.User;
import com.blog.blog.exceptions.ResourceNotFound;
import com.blog.blog.payload.UserDTO;
import com.blog.blog.repositories.UserRepo;
import com.blog.blog.services.UserService;

@Service
public class userServiceImpl implements UserService {
    @Autowired
    private UserRepo userRepositories;

    @Override
    public List<UserDTO> getAllUSer() {
        List<User> users = this.userRepositories.findAll();
        List<UserDTO> userdtods = users.stream().map(u -> this.UserToDto(u)).collect(Collectors.toList());
        return userdtods;
    }

    @Override
    public UserDTO getUserById(Integer id) {
        User user = this.userRepositories.findById(id).orElseThrow(() -> new ResourceNotFound("user","id",id));
        return this.UserToDto(user);
    }

    @Override
    public UserDTO createUser(UserDTO User) {
        User user = this.DtoToUser(User);
        User saveduser = this.userRepositories.save(user);
        return this.UserToDto(saveduser);
    }

    @Override
    public UserDTO updateUser(UserDTO User, Integer id) {
        User u = this.userRepositories.findById(id).orElseThrow(() -> new ResourceNotFound("user","id",id));
        u.setName(User.getName());
        u.setPassword(User.getPassword());
        u.setEmail(User.getEmail());
        u.setAbout(User.getAbout());

        User updatedUser = this.userRepositories.save(u);
        return this.UserToDto(updatedUser);
    }

    @Override
    public void deleteUser(int id) {
        User u = this.userRepositories.findById(id).orElseThrow(() -> new RuntimeException("user not found"));
        this.userRepositories.delete(u);
    }

    private User DtoToUser(UserDTO User) {
        User u = new User();
        u.setName(User.getName());
        u.setPassword(User.getPassword());
        u.setEmail(User.getEmail());
        u.setAbout(User.getAbout());

        return u;
    }

    private UserDTO UserToDto(User User) {
        UserDTO u = new UserDTO();
        u.setName(User.getName());
        u.setPassword(User.getPassword());
        u.setEmail(User.getEmail());
        u.setAbout(User.getAbout());

        return u;
    }
}
