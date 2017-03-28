package com.egoo.sso.server.web.exception;

public class ResourceNotFoundException extends RuntimeException {

    private static final long serialVersionUID = -2565431806475335331L;

    private String resourceName;
    private Long id;

    public ResourceNotFoundException(String resourceName, Long id) {
        this.resourceName = resourceName;
        this.id = id;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String getMessage() {
        return resourceName + " with id " + id + " is not found.";
    }

}
