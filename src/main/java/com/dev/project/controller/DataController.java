package com.dev.project.controller;


import com.dev.project.dto.DataTO;
import com.dev.project.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping(value = "/data")
@CrossOrigin(origins = "*")
public class DataController {

    @Autowired
    private DataService service;

    @PostMapping
    public DataTO save(@RequestBody DataTO dto, HttpServletRequest request) {
        return service.save(dto, request);
    }

    @PutMapping
    public DataTO update(@RequestBody DataTO dto, HttpServletRequest request) {
        return service.update(dto, request);
    }


    @GetMapping
    public List<DataTO> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public DataTO getById(@PathVariable Long id) {
        return service.getById(id);
    }
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id, HttpServletRequest request) {
        service.delete(id, request);
    }


}
