package com.usuario.gerenciamentodeusuario.repository;

import com.usuario.gerenciamentodeusuario.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<UserModel, UUID> {
    boolean existsByUserName(String userName);

    boolean existsByEmail(String email);

    boolean existsByPhoneNumber(String phoneNumber);
}
