package com.example.employeemanagement.controller;

import com.example.employeemanagement.model.Employee;
import com.example.employeemanagement.model.ResponseObject;
import com.example.employeemanagement.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class EmployeeController {
    @Autowired
    private EmployeeRepository employeeRepository;

    @GetMapping("list")
    ResponseEntity<ResponseObject> getAllEmployees() {
        List<Employee> list = employeeRepository.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Query employee successfully", list)
        );

    }

    @GetMapping("/list/{id}")
    ResponseEntity<ResponseObject> getEmployeeById(@PathVariable Long id) {
        Optional<Employee> foundedEmployee = employeeRepository.findById(id);
        if (foundedEmployee.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok", "Query employee successfully", foundedEmployee)
            );
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject("Failed", "Not found employee", "")
            );
        }
    }

    @PostMapping("/insert")
    ResponseEntity<ResponseObject> insertEmployee(@RequestBody Employee employee) {


        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("Successfully", "Insert successfully", employeeRepository.save(employee))
);
    }

    @DeleteMapping("/delete/{id}")
    ResponseEntity<ResponseObject> deleteEmployee(@PathVariable Long id) {
        boolean isExistEmployee = employeeRepository.existsById(id);
        employeeRepository.deleteById(id);
        return isExistEmployee ? ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("OK", "DELETE SUCCESSFULLY", "")
        ) : ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ResponseObject("Failed", "Delete failed", "")
        );
    }
    @PutMapping("/update/{id}")
    ResponseEntity<ResponseObject> updateEmployee(@PathVariable Long id, @RequestBody Employee newEmployee){
        Optional<Employee> employee = employeeRepository.findById(id);
        if(employee.isPresent()){
            newEmployee.setId(employee.get().getId());
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "update succesfully" , employeeRepository.save(newEmployee))
        );
    }
}
