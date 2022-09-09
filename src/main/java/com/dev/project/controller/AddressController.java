package com.dev.project.controller;


import com.dev.project.dto.AddressTO;
import com.dev.project.dto.DataTO;
import com.dev.project.service.AddressService;
import com.dev.project.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping(value = "/address")
@CrossOrigin(origins = "*")
public class AddressController {

    @Autowired
    private AddressService service;

    @PostMapping
    public AddressTO save(@RequestBody AddressTO dto, HttpServletRequest request) {
        return service.save(dto, request);
    }

    @PutMapping
    public AddressTO update(@RequestBody AddressTO dto, HttpServletRequest request) {
        return service.update(dto, request);
    }

    @GetMapping
    public List<AddressTO> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public AddressTO getById(@PathVariable Long id) {
        return service.getById(id);
    }
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id, HttpServletRequest request) {
        service.delete(id, request);
    }


}
