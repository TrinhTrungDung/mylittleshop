package com.example.mylittleshop.service;

import com.example.mylittleshop.entity.Employee;
import com.example.mylittleshop.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeService implements IntEmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public List<Employee> getAllEmployees() {
        List<Employee> employees = new ArrayList<>();
        employeeRepository.findAll().forEach(employees::add);

        return employees;
    }

    @Override
    public Employee getEmployeeByUsername(String username) {
        return employeeRepository.findByUsername(username);
    }

    @Override
    public List<Employee> getEmployeesByShopId(Long shopId) {
        return employeeRepository.findByShopId(shopId);
    }

    @Override
    public synchronized boolean addEmployee(Employee employee) {
        List<Employee> employeesExisted = getAllEmployees();

        if (employeesExisted.contains(employee)) {
            return false;
        } else {
            employeeRepository.save(employee);
            return true;
        }
    }

    @Override
    public void updateEmployee(Employee employee) {
        employeeRepository.save(employee);
    }

    @Override
    public void deleteEmployee(String username) {
        employeeRepository.delete(getEmployeeByUsername(username));
    }

}