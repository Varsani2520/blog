package com.blog.blog.services.impl;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.web.multipart.MultipartFile;

import com.blog.blog.services.ImageService;

public class ImageServiceImpl implements ImageService{

    @Override
    public String uploadImage(String path, MultipartFile file) throws IOException {

        // file name
        String name=file.getOriginalFilename();
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'uploadImage'");
    }

    @Override
    public InputStream getResource(String path, String fileName) throws FileNotFoundException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getResource'");
    }
    
}
