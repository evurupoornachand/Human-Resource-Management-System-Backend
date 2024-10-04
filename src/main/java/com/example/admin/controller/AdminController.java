package com.example.admin.controller;

import com.example.admin.model.Department;
import com.example.admin.model.Employee;
import com.example.admin.service.DepartmentService;
import com.example.admin.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private DepartmentService departmentService;

    // Fetch all employees
    @GetMapping("/employees")
    public ResponseEntity<List<Employee>> getAllEmployees() {
        List<Employee> employees = employeeService.getAllEmployees();
        return ResponseEntity.ok(employees);
    }

    // Fetch employee by ID
    @GetMapping("/employees/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
        Employee employee = employeeService.getEmployeeById(id);
        if (employee != null) {
            return ResponseEntity.ok(employee);
        } else {
            return ResponseEntity.notFound().build(); // Handle case where employee is not found
        }
    }

    // Create a new employee
    @PostMapping("/employees")
    public ResponseEntity<Employee> saveEmployee(@RequestBody Employee employee) {
        Employee savedEmployee = employeeService.createEmployee(employee); // Ensure method name matches
        return ResponseEntity.ok(savedEmployee);
    }

    // Delete employee by ID
    @DeleteMapping("/employees/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
        if (employeeService.deleteEmployee(id)) {
            return ResponseEntity.noContent().build(); // Successfully deleted
        } else {
            return ResponseEntity.notFound().build(); // Handle case where delete fails
        }
    }

    // Create a new department
    @PostMapping("/departments")
    public ResponseEntity<Department> saveDepartment(@RequestBody Department department) {
        Department savedDepartment = departmentService.createDepartment(department); // Ensure method name matches
        return ResponseEntity.ok(savedDepartment);
    }

    // Fetch all departments
    @GetMapping("/departments")
    public ResponseEntity<List<Department>> getAllDepartments() {
        List<Department> departments = departmentService.getAllDepartments();
        return ResponseEntity.ok(departments);
    }

    // Transfer an employee to a new department
    @PostMapping("/transfer/{employeeId}")
    public ResponseEntity<Void> transferEmployee(@PathVariable Long employeeId, @RequestParam String newDepartment) {
        boolean success = employeeService.transferEmployee(employeeId, newDepartment); // Ensure method exists
        if (success) {
            return ResponseEntity.ok().build(); // Transfer successful
        } else {
            return ResponseEntity.badRequest().build(); // Handle case where transfer fails
        }
    }
}
