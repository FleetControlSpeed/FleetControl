package br.com.fleetcontrol.fleetcontrol.service.exceptions;
public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String msg) {
        super(msg);
    }
}
