package org.varun.welp.Controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.varun.welp.Models.Users;
import org.varun.welp.Repositories.UserRepository;
import java.util.ArrayList;

import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("/welp/v1/users")
public class UserController {



    private UserRepository userRepository;
    public UserController( UserRepository userRepository){
        this.userRepository=userRepository;

    }

    @GetMapping
    public ResponseEntity<List<Users>> getAllUsers(){
        System.out.println("All users fetched");
        return ResponseEntity.ok(userRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUsersById(@PathVariable UUID id){

        return userRepository.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping
    public ResponseEntity<Users> AddUser(@RequestBody Users users){
        Users savedUser = (Users) userRepository.save(users);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
    }

    @PutMapping("/{id}")
    public  ResponseEntity<?> UpdateUserById(@PathVariable UUID id, @RequestBody Users updatedUser){

        return userRepository.findById(id).map(users -> {

            if (updatedUser.getName()!=null){
                users.setName(updatedUser.getName());
            }
            if (updatedUser.getEmail()!=null){
                users.setEmail(updatedUser.getEmail());
            }
            if (updatedUser.getBio()!=null){
                users.setBio(updatedUser.getBio());
            }


            Users saveduser = userRepository.save(users);
            return ResponseEntity.ok(saveduser);
        }).orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }



}
