package com.luinel.beprepared.exception;

public class EntityNotFoudException extends BadRequestException{
    public EntityNotFoudException(String message) {
        super(message);
    }
}
