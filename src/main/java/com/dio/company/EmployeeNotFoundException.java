package com.dio.company;

public class EmployeeNotFoundException extends RuntimeException{
    public EmployeeNotFoundException(long id){
        super("Could not find the id: " + id);
    }
}
