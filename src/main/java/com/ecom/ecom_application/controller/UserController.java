package com.ecom.ecom_application.controller;

import com.ecom.ecom_application.dto.UserRequest;
import com.ecom.ecom_application.dto.UserResponse;
import com.ecom.ecom_application.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

      @GetMapping
      public ResponseEntity<List<UserResponse>> getAllUsers(){
          return new ResponseEntity<>(userService.fetchAllUsers(),HttpStatus.OK);
      }

      @GetMapping("/{id}")
      public ResponseEntity<UserResponse> getUser(@PathVariable Long id){
          return userService.fetchUser(id)
                  .map(ResponseEntity::ok)
                  .orElseGet(() -> ResponseEntity.notFound().build());
      }

        @PostMapping
        public ResponseEntity<String> createUser(@RequestBody UserRequest userRequest){
            userService.addUser(userRequest);
            return new ResponseEntity<>("User Added Successfully..", HttpStatus.CREATED);
        }

      @PutMapping("/{id}")
      public ResponseEntity<String> updateUser(@RequestBody UserRequest updateUserRequest, @PathVariable Long id){
           boolean updated = userService.updateUser(updateUserRequest, id);
           if(updated){
               return ResponseEntity.ok("User updated successfully");
           }

           return ResponseEntity.notFound().build();
      }

}
