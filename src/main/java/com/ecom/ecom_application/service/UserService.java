package com.ecom.ecom_application.service;


import com.ecom.ecom_application.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.ecom.ecom_application.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private List<User> userList = new ArrayList<>();


    public List<User> fetchAllUsers(){
        return userRepository.findAll();
    }

    public Optional<User> fetchUser(Long id){
//        for(User user: userList){
//            if(user.getId().equals(id)){
//                return Optional.of(user);
//            }
//        }
//        return null;
        return userRepository.findById(id);
    }


    public void addUser(User user){
        userRepository.save(user);
    }

    public boolean updateUser(User updatedUser, Long id){
        return userRepository.findById(id)
                .map(existingUser -> {
                    existingUser.setFirstName(updatedUser.getFirstName());
                    existingUser.setLastName(updatedUser.getLastName());
                    userRepository.save(existingUser);
                    return true;
                }).orElse(false);
    }
}
