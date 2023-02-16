package com.codeplayground.department.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.codeplayground.department.model.Department;
import com.codeplayground.department.repository.DepartmentRepository;

import jakarta.validation.Valid;

@Controller
@RequestMapping(path = {"/home", "/index", "/"})
public class DepartmentController {

    @Autowired
    DepartmentRepository deptRepo;

    @GetMapping 
    public String deptListPage(Model model) {
        List<Department> departments = deptRepo.listAll();
        model.addAttribute("departments", departments);
        return "departmentlist";
    }

    @GetMapping("/addnew")
    public String deptFormPage(Model model) {
        Department department = new Department();
        model.addAttribute("department", department);
        return "departmentform";
    }
    
    @PostMapping("/addnew")
    public String addDepartment(@Valid @ModelAttribute("department") Department deptForm, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "departmentform";
        } 
        deptRepo.addDept(deptForm);  
        return "redirect:/";  
    }

    @GetMapping("/updatedept/{code}") //update with path variable code
    public String updateDept(@PathVariable("code") String code, Model model) {
        Department department = deptRepo.findByCode(code);
        model.addAttribute("department", department);
        return "departmentupdate";
    }

    @PostMapping("/updatedept")
    public String updateDeptProcess(@Valid @ModelAttribute("department") Department deptForm, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "departmentupdate";
        }
        deptRepo.updateDept(deptForm);
        return "redirect:/";
    }

    @GetMapping("/deletedept/{code}") //delete with path variable code
    public String delDept (@PathVariable("code") String code) {
        Department department = deptRepo.findByCode(code);
        deptRepo.deleteDept(department);
        return "redirect:/";
    }

}
