package com.example.moovienetwork.Controller;

import com.example.moovienetwork.Dto.LoginDto;
import com.example.moovienetwork.Dto.MovieDto;
import com.example.moovienetwork.Dto.SingUpDto;
import com.example.moovienetwork.Dto.UserDto;
import com.example.moovienetwork.Model.User;
import com.example.moovienetwork.Service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    final UserService userService;



    public UserController(UserService userService) {
        this.userService = userService;

    }

    @GetMapping
    public List<UserDto> getAllUsers(){
        return userService.getAllUsers();
    }

    @GetMapping("/me")
    public UserDto getUser(){
        return userService.getUser();
    }


    @PostMapping("/register")
    public ResponseEntity<SingUpDto> register(@RequestBody SingUpDto request){
        return ResponseEntity.ok().body(userService.register(request));
    }

    @PutMapping("/update")
    public ResponseEntity<UserDto> updateUser(@RequestBody UserDto request){
        return ResponseEntity.ok().body(userService.updateUser(request));
    }

    @PostMapping("/likeMovie")
    public String likeMovie(@RequestBody MovieDto movieDto){
       return userService.likeMovie(movieDto.getTitle());
    }





}
