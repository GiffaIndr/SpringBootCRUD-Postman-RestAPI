package com.example.demo.controller;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Employee;
import com.example.demo.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/employees")
public class EmployeeController {
    @Autowired
   private EmployeeRepository employeeRepository;

    @GetMapping
    public List<com.example.demo.model.Employee> getAllEmployees(){
        return employeeRepository.findAll();
    }

    //build create data diri REST API
    @PostMapping
    public Employee createEmployee(@RequestBody Employee employee){
        return employeeRepository.save(employee);
    }
    //build get data diri by id REST API
    @GetMapping("{id}")
    public ResponseEntity<Employee> getEmployeeById( @PathVariable long id){
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Data Diri tidak ditemukan dengan id"+id));
        return ResponseEntity.ok(employee);
    }

    //build update data diri rest API
    @PutMapping("{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable long id,@RequestBody Employee employeeDetails){
        Employee updateEmployee = employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Data diri tidak ditemukan dengan Id: " +id));

        updateEmployee.setFirstName(employeeDetails.getFirstName());
        updateEmployee.setNIS(employeeDetails.getNIS());
        updateEmployee.setEmailId(employeeDetails.getEmailId());
        updateEmployee.setRombel(employeeDetails.getRombel());

        employeeRepository.save(updateEmployee);

        return ResponseEntity.ok(updateEmployee);
    }

    //build delete data diri REST API
    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> deleteEmployee(@PathVariable long id){

        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Data diri tidak ditemukan dengan id" + id));

        employeeRepository.delete(employee);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
