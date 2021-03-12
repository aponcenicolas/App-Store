package lux.pe.na.store.security.controller;

import lux.pe.na.store.security.dto.RoleDto;
import lux.pe.na.store.security.service.RoleService;

import static lux.pe.na.store.utils.DataStatus.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/roles")
public class RoleController {

    private final RoleService service;

    @Autowired
    public RoleController(RoleService service) {
        this.service = service;
    }

    //@PreAuthorize(ROLE_USER)
    @GetMapping
    public ResponseEntity<List<RoleDto>> findAll() {
        return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
    }

    //@PreAuthorize(ROLE_ADMIN)
    @PostMapping
    public ResponseEntity<RoleDto> save(@Valid @RequestBody RoleDto roleDto) {
        return new ResponseEntity<>(service.save(roleDto), HttpStatus.CREATED);
    }
}
