package com.blog.blog;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.blog.blog.Entities.User;
import com.blog.blog.payload.UserDTO;

@SpringBootApplication
public class BlogApplication {

	public static void main(String[] args) {
		SpringApplication.run(BlogApplication.class, args);
	}
	@Bean
	public ModelMapper modelmapper(){
		ModelMapper modelmapper=new ModelMapper();
		modelmapper.typeMap(UserDTO.class, User.class).addMappings(mapper->mapper.skip(User::setId));
		return modelmapper;

	}

}
