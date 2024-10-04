package com.example.admin.service;

import com.example.admin.model.Employee;
import com.example.admin.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    
    public boolean validateEmployee(Employee employee) {
        Employee existingEmployee = employeeRepository.findByEmail(employee.getEmail());
        return existingEmployee != null && existingEmployee.getPassword().equals(employee.getPassword());
    }

    
    public Employee createEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    
    public Employee updateEmployee(Long id, Employee updatedEmployee) {
        Employee existingEmployee = employeeRepository.findById(id).orElse(null);
        if (existingEmployee != null) {
            existingEmployee.setFirstName(updatedEmployee.getFirstName());
            existingEmployee.setLastName(updatedEmployee.getLastName());
            existingEmployee.setEmail(updatedEmployee.getEmail());
            existingEmployee.setPassword(updatedEmployee.getPassword());
            return employeeRepository.save(existingEmployee);
        }
        return null; 
    }
    public boolean deleteEmployee(Long id) {
        if (employeeRepository.existsById(id)) {
            employeeRepository.deleteById(id);
            return true;
        }
        return false; 
    }

    public Employee getEmployeeById(Long id) {
        return employeeRepository.findById(id).orElse(null); 
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }
    
    public boolean transferEmployee(Long employeeId, String newDepartment) {
        Employee employee = employeeRepository.findById(employeeId).orElse(null);
        if (employee != null) {
            employee.setDepartment(newDepartment); 
            employeeRepository.save(employee);
            return true; 
        }
        return false;
    }
}
