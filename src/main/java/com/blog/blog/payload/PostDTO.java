package com.blog.blog.payload;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostDTO {
private Integer id;
    private String title;

    private String content;
    private String ImageName;
    private java.util.Date added_Date;
    private CategoryDTO category;
    private UserDTO user;

}
