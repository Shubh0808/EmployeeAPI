package com.example.employeeapi.controller;

import com.example.employeeapi.model.Employee;
import com.example.employeeapi.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService service;

    @GetMapping
    public List<Employee> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> get(@PathVariable int id) {
        Employee e = service.get(id);
        return ResponseEntity.ok(e);
    }

    @PostMapping
    public ResponseEntity<Employee> create(@RequestBody Employee e) {
        return ResponseEntity.ok(service.create(e));
    }
}
