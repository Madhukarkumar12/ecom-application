package com.ecom.ecom_application;


import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private List<User> userList = new ArrayList<>();


    public List<User> fetchAllUsers(){
        return userList;
    }

    public Optional<User> fetchUser(Long id){
        for(User user: userList){
            if(user.getId().equals(id)){
                return Optional.of(user);
            }
        }
        return null;
    }


    public List<User> addUser(User user){
        userList.add(user);
        return userList;
    }

    public boolean updateUser(User updatedUser, Long id){
        return userList.stream()
                .filter(user -> user.getId().equals(id))
                .findFirst()
                .map(existingUser -> {
                    existingUser.setFirstName(updatedUser.getFirstName());
                    existingUser.setLastName(updatedUser.getLastName());
                    return true;
                }).orElse(false);
    }
}
