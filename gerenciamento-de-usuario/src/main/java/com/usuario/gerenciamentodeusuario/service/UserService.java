package com.usuario.gerenciamentodeusuario.service;

import com.usuario.gerenciamentodeusuario.dto.UserDto;
import com.usuario.gerenciamentodeusuario.model.UserModel;
import com.usuario.gerenciamentodeusuario.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    final
    UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public Object save(UserDto userDto){

        BCryptPasswordEncoder enccrypt = new BCryptPasswordEncoder();
        String passwordEncrypt = enccrypt.encode(userDto.getPassword());

        if(userRepository.existsByUserName(userDto.getUserName())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("This user already exists. Please choose another");
        }

        if(userRepository.existsByEmail(userDto.getEmail())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("This email is already registered.");
        }

        if(userRepository.existsByPhoneNumber(userDto.getPhoneNumber())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("This phone is already registered.");
        }

        var userModel = new UserModel();
        BeanUtils.copyProperties(userDto, userModel);
        userModel.setRegistrationDate(LocalDateTime.now());
        userModel.setPassword(passwordEncrypt);
        return userRepository.save(userModel);
    }

    public Object getAllUsers(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    public Object findById(UUID id) {
        Optional<UserModel> userModelOptional = userRepository.findById(id);
        if(!userModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found.");
        }
        return userModelOptional.get();
    }

    @Transactional
    public Object delete(UUID id) {
        Optional<UserModel> userModelOptional = userRepository.findById(id);
        if(!userModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found.");
        }
        userRepository.delete(userModelOptional.get());
        return "Deleted sucessfuly";
    }

    public Object update(UserDto userDto, UUID id){

        BCryptPasswordEncoder enccrypt = new BCryptPasswordEncoder();
        String passwordEncrypt = enccrypt.encode(userDto.getPassword());

        Optional<UserModel> userModelOptional = userRepository.findById(id);

        if(userModelOptional.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found.");
        }

        var userModel = new UserModel();
        BeanUtils.copyProperties(userDto, userModel);
        userModel.setRegistrationDate(userModelOptional.get().getRegistrationDate());
        userModel.setId(userModelOptional.get().getId());
        userModel.setPassword(passwordEncrypt);

        return userRepository.save(userModel);
    }

}
