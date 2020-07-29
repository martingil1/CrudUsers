package com.crud.login.configurations;

import com.crud.login.exceptions.InvalidPasswordException;
import com.crud.login.services.UserService;
import com.kastkode.springsandwich.filter.api.BeforeHandler;
import com.kastkode.springsandwich.filter.api.Flow;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserRoleRestriction implements BeforeHandler {

    private final UserService userService;

    @Override
    public Flow handle(HttpServletRequest request, HttpServletResponse response, HandlerMethod handler, String[] flags) throws Exception {
        String userEmail = Optional.ofNullable(request.getHeader("USER_EMAIL"))
                .orElseThrow(InvalidPasswordException::new);
        String userPassword = Optional.ofNullable(request.getHeader("USER_PASSWORD"))
                .orElseThrow(InvalidPasswordException::new);

        userPermission(userEmail, userPassword);

        return Flow.CONTINUE;
    }

    public void userPermission(String userEmail, String userPassword) {
        if (!userService.existsByEmailAndPassword(userEmail, userPassword)) {
            throw new InvalidPasswordException();
        }
    }

}
