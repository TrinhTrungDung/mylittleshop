package com.example.mylittleshop.repository;

import com.example.mylittleshop.entity.Employee;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface EmployeeRepository extends CrudRepository<Employee,Long> {
    List<Employee> findByShopId(Long shopId);
    Employee findByUsername(String username);
}