package com.example.HotelService.exceptions;

public class ResourceNotFoundException extends RuntimeException{

    public ResourceNotFoundException(String message){
        super(message);
    }
    public ResourceNotFoundException(){
        System.out.println("Hotel id is not found");
    }

}
