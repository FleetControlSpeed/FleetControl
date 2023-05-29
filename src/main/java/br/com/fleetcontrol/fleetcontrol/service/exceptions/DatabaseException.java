package br.com.fleetcontrol.fleetcontrol.service.exceptions;
public class DatabaseException extends RuntimeException{
    public DatabaseException(String msg){
        super(msg);
    }
}