package com.crud.login.repositories;

import com.crud.login.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    Boolean existsByEmail(String email);

    Boolean existsByEmailAndPassword(String email, String password);

    User findByIdAndPassword(Integer id, String password);
}
