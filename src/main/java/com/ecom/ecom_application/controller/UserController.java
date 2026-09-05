package com.ecom.ecom_application.controller;

import com.ecom.ecom_application.model.User;
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


      @GetMapping("")
//      @RequestMapping(value = "/api/users", method = RequestMethod.GET)
      public ResponseEntity<List<User>> getAllUsers(){
          return new ResponseEntity<>(userService.fetchAllUsers(),HttpStatus.OK);
      }

      @GetMapping("/{id}")
      public ResponseEntity<User> getUser(@PathVariable Long id){
//          User user = userService.fetchUser(id);
//          if(user == null){
//              return ResponseEntity.notFound().build();
//          }
//          return ResponseEntity.ok(user);
          return userService.fetchUser(id)
                  .map(ResponseEntity::ok)
                  .orElseGet(() -> ResponseEntity.notFound().build());

      }


      @PutMapping("/{id}")
      public ResponseEntity<String> updateUser(@RequestBody User user, @PathVariable Long id){
           boolean updated = userService.updateUser(user, id);
           if(updated){
               return ResponseEntity.ok("User updated successfully");
           }

           return ResponseEntity.notFound().build();
      }

      @PostMapping("")
      public ResponseEntity<String> createUser(@RequestBody User user){
          userService.addUser(user);
          return new ResponseEntity<>("User Added Successfully..", HttpStatus.CREATED);
      }
}
