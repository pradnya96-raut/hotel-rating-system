package com.example.MyUserService.exceptions;

public class ResourceNotFoundException extends RuntimeException{

    public ResourceNotFoundException(String message){
        super(message);
    }

    public ResourceNotFoundException(){
        System.out.println("Resource not found on server !!");
    }
}
