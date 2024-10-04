package com.example.admin.controller;

import com.example.admin.model.Admin;
import com.example.admin.model.Employee;
import com.example.admin.service.AdminService;
import com.example.admin.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/login")
public class LoginController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/admin")
    public ResponseEntity<?> adminLogin(@RequestBody Admin admin) {
        boolean isValid = adminService.validateAdmin(admin);
        if (isValid) {
            return ResponseEntity.ok("Admin login successful");
        } else {
            return ResponseEntity.status(401).body("Invalid credentials");
        }
    }

    @PostMapping("/employee")
    public ResponseEntity<?> employeeLogin(@RequestBody Employee employee) {
        boolean isValid = employeeService.validateEmployee(employee);
        if (isValid) {
            return ResponseEntity.ok("Employee login successful");
        } else {
            return ResponseEntity.status(401).body("Invalid credentials");
        }
    }
}
