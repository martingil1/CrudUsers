package com.crud.login.controllers;

import com.crud.login.models.dtos.AddUserDTO;
import com.crud.login.models.responses.UserResponse;
import com.crud.login.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;

import java.net.URI;

import static com.crud.login.utils.UserRouter.USER_URL;

@RequiredArgsConstructor
@RestController
@RequestMapping(USER_URL)
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity addUser (@Valid @RequestBody AddUserDTO userDTO){

        return ResponseEntity.created(getLocation(UserResponse.fromUser(userService
                .saveUser(userDTO)))).build();
    }

    private URI getLocation(UserResponse userResponse) {

        return ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path(USER_URL)
                .buildAndExpand(userResponse.getId())
                .toUri();
    }

}
