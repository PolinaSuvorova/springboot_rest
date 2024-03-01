package com.polina.spring.springboot.springboot_rest.dao;

import com.polina.spring.springboot.springboot_rest.entity.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeesDAOImpl implements EmployeeDAO {

    @Autowired
    private EntityManager entityManager;

    @Override
    public List<Employee> getAllEmployees() {
        Query query = entityManager.createQuery("from Employee ");
        return query.getResultList();
    }

    @Override
    public void saveEmployee(Employee employee) {
        Employee emp = entityManager.merge(employee);// save or update
        employee.setId(emp.getId());
    }

    @Override
    public Employee getEmployee(int id) {
        return entityManager.find(Employee.class, id);
    }

    @Override
    public void deleteEmployee(int id) {
        Query query = entityManager.createQuery("delete from Employee " +
                " where id=:empId");
        query.setParameter("empId", id);
        query.executeUpdate();
    }
}
