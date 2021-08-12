package com.example.socialMedia.demo.securty;

public enum ApplicationUserPermission {
    NORMAL_READ_PUBLIC_POST("publicpost:read"),
    NORMAL_WRITE_PUBLIC_POST("requsetforpublicpost:write"),
    NORMAL_READ_PRIVATE_POST("privatepost:read"),
    NORMAL_WRITE_PRIVATE_POST("privatepost:read"),
    NORMAL_COMMENT_IN_POST("comment:write"),
    NORMAL_JOIN_IN_GROUP("group:write"),
    NORMAL_CREATE_GROUP("requestforcreategroupe:write"),
    NORMAL_ACCEPT_PRIVATE_POST("privatepost:write"),
    NORMAL_ACCEPT_JOIN_IN_GROUP("group:write"),
    NORMAL_READ_NORMAL("normaluser.read"),
    ADMIN_ACCEPT_GROUP("group:write"),
    ADMIN_ACCCEPT_PUBLIC_POST("publicpost:write"),
    ADMIN_WRITE_NORMAL("normaluser.write"),
    ADMIN_READ_NORMALS("normaluser:read") ;

    private final String permission;

    ApplicationUserPermission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }

}
