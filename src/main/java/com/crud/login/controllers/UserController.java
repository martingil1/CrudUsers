package com.crud.login.controllers;

import com.crud.login.configurations.UserRoleRestriction;
import com.crud.login.models.dtos.AddUserDTO;
import com.crud.login.models.responses.UserResponse;
import com.crud.login.services.UserService;
import com.kastkode.springsandwich.filter.annotation.Before;
import com.kastkode.springsandwich.filter.annotation.BeforeElement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

import static com.crud.login.utils.UserRouter.USER_ID_URL;
import static com.crud.login.utils.UserRouter.USER_URL;

@RequiredArgsConstructor
@RestController
@RequestMapping(USER_URL)
@Before(@BeforeElement(UserRoleRestriction.class))
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity addUser(@Valid @RequestBody AddUserDTO userDTO) {

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

    @DeleteMapping(USER_ID_URL)
    public ResponseEntity<UserResponse> deleteUser(@RequestHeader("USER_PASSWORD") String userPassword, @PathVariable(value = "user-id") Integer idUser) {

        return ResponseEntity.ok(UserResponse.fromUser(userService.delete(userPassword, idUser)));
    }

}
