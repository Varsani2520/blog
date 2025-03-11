package com.blog.blog.payload;

import org.springframework.data.annotation.Id;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CategoryDTO {
    
    private Integer id;
    @NotBlank(message = "title cannot be empty")
    private String title;
    @NotBlank(message = "desc cannot be empty")
    private String description;
   
}
