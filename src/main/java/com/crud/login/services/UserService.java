package com.crud.login.services;

import com.crud.login.exceptions.EmailExistException;
import com.crud.login.models.User;
import com.crud.login.models.dtos.AddUserDTO;
import com.crud.login.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    public User saveUser(AddUserDTO newUser) {

        if (userRepository.existsByEmail(newUser.getEmail())) {
            throw new EmailExistException();
        }

        return userRepository.save(User.fromDto(newUser));
    }

    public Boolean existsByEmailAndPassword(String email, String password) {

        return userRepository.existsByEmailAndPassword(email, password);
    }

    public User delete(String password, Integer idUser) {

        User deletedUser = userRepository.findByIdAndPassword(idUser, password);
        userRepository.delete(deletedUser);

        return deletedUser;
    }

}
