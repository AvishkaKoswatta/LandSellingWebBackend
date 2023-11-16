package com.example.Projecttwo.Exception;

public class LandNotFoundException extends RuntimeException{
    public LandNotFoundException(Long id){
        super("Land not found id"+id);
    }
}
