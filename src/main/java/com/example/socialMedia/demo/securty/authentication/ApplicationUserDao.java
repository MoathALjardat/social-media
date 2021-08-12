package com.example.socialMedia.demo.securty.authentication;

import java.util.Optional;

public interface ApplicationUserDao {
     Optional<ApplicationUser> selectAppUserByUsername (String username) ;
}
