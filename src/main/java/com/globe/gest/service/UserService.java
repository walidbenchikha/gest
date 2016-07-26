package com.globe.gest.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.globe.gest.exception.DuplicateUserException;
import com.globe.gest.exception.UserNotFoundException;
import com.globe.gest.model.User;

public interface UserService extends UserDetailsService {

    public void addUser(User user) throws DuplicateUserException;

    public User getUser(int userId) throws UserNotFoundException;

    public User getUser(String username) throws UserNotFoundException;

    public void updateUser(User user) throws UserNotFoundException, DuplicateUserException;
    
    public void deleteUser(int userId) throws UserNotFoundException;

    public List<User> getUsers();
    public List<User> getAuditors();
    public List<User> getAuditorsByName(String name,String mail);

	
}
