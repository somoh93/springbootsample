package com.somlabs.springbootsample.services;

import com.somlabs.springbootsample.exceptions.UserException;
import com.somlabs.springbootsample.model.User;
import com.somlabs.springbootsample.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User createOrUpdateUser(User user){
        try{
            user.setUsername(user.getUsername().toLowerCase());
            return userRepository.save(user);
        } catch(Exception e){
            throw new UserException("Error occured during the save operation for "+user.getUsername());
        }
    }

    public User findByUsername(String username){
        User user = userRepository.findByUsername(username);
        if(user == null){
            throw new UserException(username+" not found!");
        }
        return user;
    }

    public Iterable<User> findAllUsers(){
        return userRepository.findAll();
    }

    public void deleteUserByUsername(String username){
        User user = userRepository.findByUsername(username);
        if(user == null){
            throw new UserException("Cannot delete a non-exisiting user "+username);
        }
        userRepository.delete(user);
    }
}
