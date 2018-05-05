package com.example.mylittleshop.service;

import com.example.mylittleshop.entity.Employee;

import java.util.List;

public interface IntEmployeeService {
    List<Employee> getAllEmployees();
    Employee getEmployeeByUsername(String username);
    List<Employee> getEmployeesByShopId(Long shopId);
    boolean addEmployee(Employee employee);
    void updateEmployee(Employee employee);
    void deleteEmployee(String username);
}