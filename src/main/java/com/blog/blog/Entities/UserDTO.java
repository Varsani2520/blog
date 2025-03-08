package com.blog.blog.Entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserDTO {
    
    private int id;
    private String name;
    private String email;
    private String password;
    private String about; 
}
