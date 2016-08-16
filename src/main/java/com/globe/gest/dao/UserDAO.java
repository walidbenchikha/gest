package com.globe.gest.dao;

import java.util.List;

import com.globe.gest.exception.DuplicateUserException;
import com.globe.gest.exception.UserNotFoundException;
import com.globe.gest.model.User;

public interface UserDAO {

    public void addUser(User user) throws DuplicateUserException;

    public User getUser(int userId);
    
    public User getUser(String username) throws UserNotFoundException;

    public void updateUser(User user) throws UserNotFoundException, DuplicateUserException;

    public void deleteUser(int userId) throws UserNotFoundException;

    public List<User> getUsers();
    
    public List<User> getAuditors();
    
    public List<User> getAuditorsByName(String name, String mail);

}
