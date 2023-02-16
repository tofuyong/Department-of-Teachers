package com.codeplayground.department.service;

import org.springframework.stereotype.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.codeplayground.department.model.Department;
import com.codeplayground.department.repository.DepartmentRepository;

@Service
public class DepartmentService {

    @Autowired
    private DepartmentRepository deptRepo;

    public List<Department> getAllDept() {
        return deptRepo.listAll();
    }

    public void addDept(Department department) {
        deptRepo.addDept(department);
    }

    public Department findByCode(String code) {
        Department dept = deptRepo.listAll().stream().filter(d -> d.getCode().equals(code)).findFirst().get();
        return dept;
    }

    public int getDeptIndex(Department dept) {
        Department deptToUpdate = deptRepo.listAll().stream().filter(d -> d.getCode().equals(dept.getCode())).findFirst().get();
        int deptIndex = deptRepo.listAll().indexOf(deptToUpdate);
        return deptIndex;
    } 

    public void updateDept(Department dept) {
        deptRepo.updateDept(dept);
    }

    public void deleteDept(Department dept) {
        deptRepo.deleteDept(dept);
    }
}
