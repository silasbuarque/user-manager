package com.usuario.gerenciamentodeusuario.controller;

import com.usuario.gerenciamentodeusuario.dto.UserDto;
import com.usuario.gerenciamentodeusuario.service.UserService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserController {

    final
    UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/create")
    public ResponseEntity<Object> saveUser(@RequestBody @Valid UserDto userDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(userDto));
    }

    @GetMapping
    public ResponseEntity<Object> getAllUsers(@PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable){
        return ResponseEntity.status(HttpStatus.OK).body(userService.getAllUsers(pageable));
    }

    @GetMapping({"/{id}"})
    public ResponseEntity<Object> getOneUser(@PathVariable(value = "id") UUID id){
        return ResponseEntity.status(HttpStatus.OK).body(userService.findById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteUser(@PathVariable(value = "id") UUID id){
        return ResponseEntity.status(HttpStatus.OK).body(userService.delete(id));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Object> updateUser(@PathVariable(value = "id") UUID id,
                                             @RequestBody @Valid UserDto userDto){
        return ResponseEntity.status(HttpStatus.OK).body(userService.update(userDto, id));

    }


}
