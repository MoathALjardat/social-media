package com.example.socialMedia.demo.services;

import com.example.socialMedia.demo.models.User;
import com.example.socialMedia.demo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;


    public int getNumberOfUsers() {
        return (int) userRepository.count();
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public User getUserById(int id) {
        return userRepository.findById(id).orElse(null);
    }

    public User addUser(User user) {
        return userRepository.save(user);
    }

    public User deleteUser(int id) {
        User deletedUser = userRepository.findById(id).orElse(null);
        userRepository.deleteById(id);
        return deletedUser;
    }

    public User updateUser(User user) {
        userRepository.save(user);
        return user;
    }

    public User findUserByUsername(String username)
    {
        User user =
                userRepository.findAll().stream()
                        .filter(normalUser1 -> username.equals(normalUser1.getUsername()))
                        .findAny()
                        .orElse(null);

        return user;

    }

    public  User getCurrentUser ()
    {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        String username = "";
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        } else {
            username = principal.toString();
        }

        if (username.equals(""))
            return null;

        return findUserByUsername(username);
    }

}
