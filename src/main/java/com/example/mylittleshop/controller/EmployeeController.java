package com.example.mylittleshop.controller;

import com.example.mylittleshop.entity.Employee;
import com.example.mylittleshop.json.EmployeeInfo;
import com.example.mylittleshop.service.IntEmployeeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {

    @Autowired
    private IntEmployeeService employeeService;

    @GetMapping(value = "employees", produces={MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<EmployeeInfo>> getAllEmployees() {
        List<EmployeeInfo> responseEmployeeList = new ArrayList<>();
        List<Employee> employeeList = employeeService.getAllEmployees();

        for (Employee employee : employeeList) {
            EmployeeInfo employeeInfo = new EmployeeInfo();
            BeanUtils.copyProperties(employee, employeeInfo);
            responseEmployeeList.add(employeeInfo);
        }

        return new ResponseEntity<>(responseEmployeeList, HttpStatus.OK);
    }

    @GetMapping(value="employee/{username}", produces={MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<EmployeeInfo> getEmployeeByUsername(@PathVariable("username") String username) {
        EmployeeInfo employeeInfo = new EmployeeInfo();
        BeanUtils.copyProperties(employeeService.getEmployeeByUsername(username), employeeInfo);

        return new ResponseEntity<>(employeeInfo, HttpStatus.OK);
    }

    @GetMapping(value="employee/{shopId}", produces={MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<EmployeeInfo>> getEmployeesByShopId(@PathVariable("shopId") Long shopId) {
        List<EmployeeInfo> responseEmployeeList = new ArrayList<>();
        List<Employee> employeeList = employeeService.getEmployeesByShopId(shopId);

        for (Employee employee : employeeList) {
            EmployeeInfo employeeInfo = new EmployeeInfo();
            BeanUtils.copyProperties(employee, employeeInfo);
            responseEmployeeList.add(employeeInfo);
        }

        return new ResponseEntity<>(responseEmployeeList, HttpStatus.OK);
    }

    @PostMapping(value="employee", produces={MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Void> addEmployee(@RequestBody EmployeeInfo employeeInfo, UriComponentsBuilder builder) {
        Employee employee = new Employee();
        BeanUtils.copyProperties(employeeInfo, employee);
        boolean flag = employeeService.addEmployee(employee);

        if (!flag) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        HttpHeaders headers = new HttpHeaders();

        headers.setLocation(builder.path("/employee/{username}").buildAndExpand(employee.getUsername()).toUri());

        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @PutMapping(value="employee", produces={MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<EmployeeInfo> updateEmployee(@RequestBody EmployeeInfo employeeInfo) {
        Employee employee = new Employee();
        BeanUtils.copyProperties(employeeInfo, employee);
        employeeService.updateEmployee(employee);

        EmployeeInfo newEmployeeInfo = new EmployeeInfo();
        BeanUtils.copyProperties(employee, newEmployeeInfo);

        return new ResponseEntity<>(newEmployeeInfo, HttpStatus.OK);
    }

    @DeleteMapping(value="employee/{username}", produces={MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Void> deleteEmployee(@PathVariable("username") String username) {
        employeeService.deleteEmployee(username);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
