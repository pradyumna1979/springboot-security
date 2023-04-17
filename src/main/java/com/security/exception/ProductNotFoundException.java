package com.security.exception;

public class ProductNotFoundException extends Throwable{
    public ProductNotFoundException(String string){
        super(string);
    }
}
