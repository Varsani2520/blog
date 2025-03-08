package com.blog.blog.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResourceNotFound extends RuntimeException {
    String ResourceName;
    String FieldName;
    int FieldValue;

    public ResourceNotFound(String resourceName, String fieldName, int fieldValue) {
        super(String.format("%s not found with %s:%d", resourceName, fieldName, fieldValue));
        ResourceName = resourceName;
        FieldName = fieldName;
        FieldValue = fieldValue;
    }

}
