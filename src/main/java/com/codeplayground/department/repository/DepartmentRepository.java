package com.codeplayground.department.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import com.codeplayground.department.model.Department;

@Repository
public class DepartmentRepository {
    @Autowired
    RedisTemplate<String, Object> redisTemplate;

    public void save (Department dept) {
        redisTemplate.opsForList().leftPush("departmentlist", dept.getCode());  
        redisTemplate.opsForHash().put("departmentmap", dept.getCode(), dept); //name of map, key, value
    }

    public Department findByCode(String code){
        return (Department) redisTemplate.opsForHash().get("departmentmap", code);
    }

    public List<Department> findAll() {
        System.out.println("current size of map: " + redisTemplate.opsForHash().size("departmentmap")); // testing
        System.out.println("current size of list: " + redisTemplate.opsForList().size("departmentlist"));

        List<Object> deptList = redisTemplate.opsForList().range("departmentlist", 0, 30); //problem started here
        List<Department> depts = redisTemplate.opsForHash() 
                    .multiGet("departmentmap", deptList)
                    .stream()
                    .filter(Department.class::isInstance)  
                    .map(Department.class::cast)
                    .toList();          
        return depts;
    }

    public void delete(Department dept) {
        redisTemplate.opsForHash().delete("departmentmap", dept.getCode()); //delete key
        redisTemplate.opsForList().remove("departmentlist", 0 , dept.getCode());  
    }

    public void update(Department dept) {
        redisTemplate.opsForHash().put("departmentmap", dept.getCode(), dept); 
    }
    
}






// private List<Department> allDepartments = new ArrayList<>();

// public DepartmentRepository() {
//     allDepartments.add(new Department("100", "English", 10));
//     allDepartments.add(new Department("200", "Mother Tongue", 20));
//     allDepartments.add(new Department("300", "Science", 30));
//     allDepartments.add(new Department("400", "Math", 40));
//     allDepartments.add(new Department("500", "Coding", 0));
// }

// public List<Department> listAll(){
//     return allDepartments;
// }

// public void addDept(Department department) {
//     this.allDepartments.add(department);
// }

// public void updateDept(Department dept) {
//     Department deptToUpdate = allDepartments.stream().filter(d -> d.getCode().equals(dept.getCode())).findFirst().get();
//     int deptIndex = allDepartments.indexOf(deptToUpdate);

//     if (deptIndex >= 0) {
//         allDepartments.remove(deptIndex); //deletes existing dept record if present
//     }
//     allDepartments.add(dept); //replaces with updated department record
// }

// public void deleteDept(Department dept) {
//     int deptIndex = allDepartments.indexOf(dept);
//     if (deptIndex >= 0) {
//         allDepartments.remove(deptIndex); //deletes existing dept record if present
//     }
// }

