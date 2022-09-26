package com.practice.angularCrud.repository;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.practice.angularCrud.entity.Employee;

@Qualifier("employee")
@Repository
public interface employeeRepo extends JpaRepository<Employee, Integer>{

}
