package com.example.socialMedia.demo.securty;

import com.google.common.collect.Sets;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

import static com.example.socialMedia.demo.securty.ApplicationUserPermission.*;
import static com.google.common.collect.Sets.newHashSet;

public enum ApplicationUserRole {

    NORMAL(newHashSet(
            NORMAL_READ_NORMAL,
            NORMAL_ACCEPT_JOIN_IN_GROUP,
            NORMAL_JOIN_IN_GROUP,
            NORMAL_ACCEPT_PRIVATE_POST,
            NORMAL_COMMENT_IN_POST,
            NORMAL_CREATE_GROUP,
            NORMAL_READ_PRIVATE_POST,
            NORMAL_READ_PUBLIC_POST,
            NORMAL_WRITE_PUBLIC_POST,
            NORMAL_WRITE_PRIVATE_POST)),
    ADMIN(Sets.newHashSet(
            NORMAL_READ_NORMAL,
            NORMAL_ACCEPT_JOIN_IN_GROUP,
            NORMAL_JOIN_IN_GROUP,
            NORMAL_ACCEPT_PRIVATE_POST,
            NORMAL_COMMENT_IN_POST,
            NORMAL_CREATE_GROUP,
            NORMAL_READ_PRIVATE_POST,
            NORMAL_READ_PUBLIC_POST,
            NORMAL_WRITE_PUBLIC_POST,
            NORMAL_WRITE_PRIVATE_POST,
            ADMIN_ACCEPT_GROUP,
            ADMIN_ACCCEPT_PUBLIC_POST,
            ADMIN_WRITE_NORMAL ,
            ADMIN_READ_NORMALS
    ));


    private final Set<ApplicationUserPermission> permissions;

    ApplicationUserRole(Set<ApplicationUserPermission> permissions) {
        this.permissions = permissions;
    }

    public Set<ApplicationUserPermission> getPermissions() {
        return permissions;
    }

    public Set<SimpleGrantedAuthority> getGrantedAuthorities() {
        Set<SimpleGrantedAuthority> permissions =
                getPermissions().stream()
                        .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                        .collect(Collectors.toSet());

        permissions.add(new SimpleGrantedAuthority("ROLE_" + this.name()));

        return permissions;
    }
}
