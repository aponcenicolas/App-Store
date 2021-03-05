package lux.pe.na.store.security.controller;

import lux.pe.na.store.security.dto.UserDto;
import lux.pe.na.store.security.dto.UserEditDto;
import lux.pe.na.store.security.service.UserService;

import static lux.pe.na.store.utils.DataStatus.*;

import lux.pe.na.store.utils.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService service;

    @Autowired
    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> findAll() {
        return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getById(@PathVariable Long id) {
        return new ResponseEntity<>(service.getById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Object> save(@Valid @RequestBody UserDto userDto) {
        if (service.existsByUserName(userDto.getUserName()))
            return new ResponseEntity<>(new Message(USER_EXISTS), HttpStatus.BAD_REQUEST);
        if (service.existsByMail(userDto.getMail()))
            return new ResponseEntity<>(new Message(USER_MAIL), HttpStatus.BAD_REQUEST);
        service.save(userDto);
        return new ResponseEntity<>(new Message(USER_OK), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserEditDto> update(@PathVariable Long id, @Valid @RequestBody UserEditDto userEditDto) {
        return new ResponseEntity<>(service.update(id, userEditDto), HttpStatus.CREATED);
    }
}
