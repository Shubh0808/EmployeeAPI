package com.example.employeeapi.service;

import com.example.employeeapi.model.Employee;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class EmployeeService {

    private final Map<Integer, Employee> employees = new HashMap<>();

    public List<Employee> getAll() {
        return new ArrayList<>(employees.values());
    }

    public Employee get(int id) {
        return employees.get(id);
    }

    public Employee create(Employee e) {
        employees.put(e.getId(), e);
        return e;
    }
}
