package com.codeplayground.department.controller;

import java.util.Collections;
import java.util.List;

import org.apache.logging.log4j.util.PropertySource.Comparator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.comparator.Comparators;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.codeplayground.department.model.Department;
import com.codeplayground.department.service.DepartmentService;
import jakarta.validation.Valid;

@Controller
@RequestMapping(path = {"/home", "/index", "/"})
public class DepartmentController {

    @Autowired
    DepartmentService deptService;

    @GetMapping  
    public String deptListPage(Model model) {
        List<Department> departments = deptService.getAllDept();
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
        deptService.addDept(deptForm); 
        model.addAttribute( "dept", deptForm);
        System.out.println("added new department: " + deptForm); //testing
        return "redirect:/";  
    }


    @GetMapping("/updatedept/{code}") //update with path variable code
    public String updateDept(@PathVariable("code") String code, Model model) {
        Department department = deptService.findByCode(code);
        model.addAttribute("department", department);
        return "departmentupdate";
    }

    @PostMapping("/updatedept")
    public String updateDeptProcess(@Valid @ModelAttribute("department") Department deptForm, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "departmentupdate";
        }
        deptService.updateDept(deptForm);
        return "redirect:/";
    }

    @GetMapping("/deletedept/{code}") //delete with path variable code
    public String delDept (@PathVariable("code") String code) {
        Department department = deptService.findByCode(code);
        deptService.deleteDept(department);
        return "redirect:/";
    }
}





// @Controller
// @RequestMapping(path = {"/home", "/index", "/"})
// public class DepartmentController {

//     @Autowired
//     DepartmentService deptService;

//     @GetMapping 
//     public String deptListPage(Model model) {
//         List<Department> departments = deptService.getAllDept();
//         model.addAttribute("departments", departments);
//         return "departmentlist";
//     }

//     @GetMapping("/addnew")
//     public String deptFormPage(Model model) {
//         Department department = new Department();
//         model.addAttribute("department", department);
//         return "departmentform";
//     }
    
//     @PostMapping("/addnew")
//     public String addDepartment(@Valid @ModelAttribute("department") Department deptForm, BindingResult result, Model model) {
//         if (result.hasErrors()) {
//             return "departmentform";
//         } 
//         deptService.addDept(deptForm);  
//         return "redirect:/";  
//     }

//     @GetMapping("/updatedept/{code}") //update with path variable code
//     public String updateDept(@PathVariable("code") String code, Model model) {
//         Department department = deptService.findByCode(code);
//         model.addAttribute("department", department);
//         return "departmentupdate";
//     }

//     @PostMapping("/updatedept")
//     public String updateDeptProcess(@Valid @ModelAttribute("department") Department deptForm, BindingResult result, Model model) {
//         if (result.hasErrors()) {
//             return "departmentupdate";
//         }
//         deptService.updateDept(deptForm);
//         return "redirect:/";
//     }

//     @GetMapping("/deletedept/{code}") //delete with path variable code
//     public String delDept (@PathVariable("code") String code) {
//         Department department = deptService.findByCode(code);
//         deptService.deleteDept(department);
//         return "redirect:/";
//     }
// }
