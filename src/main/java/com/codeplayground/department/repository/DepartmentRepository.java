package com.codeplayground.department.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.codeplayground.department.model.Department;

@Repository
public class DepartmentRepository {
    private List<Department> allDepartments = new ArrayList<>();

    public DepartmentRepository() {
        allDepartments.add(new Department("100", "Coding", 10));
        allDepartments.add(new Department("200", "Baking", 20));
        allDepartments.add(new Department("300", "PE", 30));
        allDepartments.add(new Department("400", "Science", 40));
    }

    public List<Department> listAll(){
        return allDepartments;
    }
    
    public void addDept(Department department) {
        this.allDepartments.add(department);
    }
    
}
