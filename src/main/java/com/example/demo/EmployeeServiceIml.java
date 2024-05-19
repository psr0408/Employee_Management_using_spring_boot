package com.example.demo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;

import com.example.EmployEntity;
import com.example.Employeerespository;

public class EmployeeServiceIml implements EmployeeService {
    private Employeerespository employeerespository;
    List<Employee> employees = new ArrayList<>();

    @Override
    public Employee readEmployee(Long id) {
        EmployEntity emp = employeerespository.findById(id).get();
        // EmployEntity employEntity = employeerespository.findById(id).get();
        Employee employee = new Employee();
        BeanUtils.copyProperties(emp, employee);
        // BeanUtils.copyProperties(employEntity, employee);
        return employee;
    }

    @Override
    public List<Employee> readEmployees() {
        List<EmployEntity> employeesList = employeerespository.findAll();
        List<Employee> employees = new ArrayList<>();
        for (EmployEntity employEntity : employeesList) {
            Employee emp = new Employee();
            emp.setName(employEntity.getName());
            emp.setMail(employEntity.getMail());
            emp.setId(employEntity.getId());
            emp.setPhone(employEntity.getPhone());

            employees.add(emp);
        }
        return employees;
    }

    @Override
    public boolean deleteEmployee(Long id) {
        // employees.remove(id);
        EmployEntity emp = employeerespository.findById(id).get();
        employeerespository.delete(emp);

        return true;
    }

    public String createEmployee(Employee employee) {
        EmployEntity employEntity = new EmployEntity();
        BeanUtils.copyProperties(employee, employEntity);
        employeerespository.save(employEntity);
        // employees.add(employee);
        return "Saved Successfully";
    }

    @Override
    public String updateEmployee(Long id, Employee employee) {
        EmployEntity exestingEmployee = employeerespository.findById(id).get();
        exestingEmployee.setName(employee.getName());
        exestingEmployee.setPhone(employee.getPhone());
        exestingEmployee.setMail(employee.getMail());

        employeerespository.save(exestingEmployee);
        return "Update Successfully";
    }

    // @Override
    // public Employee readEmployee() {

    // }
}
