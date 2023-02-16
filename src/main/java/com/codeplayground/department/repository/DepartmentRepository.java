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

    public Department findByCode(String code) {
        Department dept = allDepartments.stream().filter(d -> d.getCode().equals(code)).findFirst().get();
        return dept;
    }

    public void updateDept(Department dept) {
        Department deptToUpdate = allDepartments.stream().filter(d -> d.getCode().equals(dept.getCode())).findFirst().get();
        int deptIndex = allDepartments.indexOf(deptToUpdate);

        if (deptIndex >= 0) {
            allDepartments.remove(deptIndex); //deletes existing dept record if present
        }
        allDepartments.add(dept); //replaces with updated department record
    }

    public void deleteDept(Department dept) {
        int deptIndex = allDepartments.indexOf(dept);
        if (deptIndex >= 0) {
            allDepartments.remove(deptIndex); //deletes existing dept record if present
        }
    }
    
}
