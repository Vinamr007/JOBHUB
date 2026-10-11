package com.jobhub.controller;

import com.jobhub.dto.UserRegistrationRequest;
import com.jobhub.entity.User;
import com.jobhub.service.UserService;
import jakarta.validation.Valid;
import com.jobhub.dto.UserResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {

private final UserService userService;

public UserController(UserService userService){
    this.userService=userService;
}
//old method
//@PostMapping
//    public User createUser(@RequestBody User user){
//   return userService.createUser(user);
//}

    // new method
    @PostMapping
    public UserResponse createUser(@Valid @RequestBody UserRegistrationRequest request) {

        User user = new User();
        user.setFullName(request.getFullName());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        user.setRole("JOB_SEEKER");

        User savedUser = userService.createUser(user);

        return new UserResponse(
                savedUser.getId(),
                savedUser.getFullName(),
                savedUser.getEmail(),
                savedUser.getRole()
        );
    }
}
