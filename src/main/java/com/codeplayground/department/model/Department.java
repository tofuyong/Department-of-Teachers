package com.codeplayground.department.model;

import java.io.Serializable;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class Department implements Serializable {

    @NotEmpty(message = "Department code is a mandatory field")
    @Size(min=3, max=3, message="Department code must contain 3 digits")
    private String code;

    @NotEmpty(message = "Department name is a mandatory field")
    private String name;

    @Min(value=0, message="Minimum number of teachers in a department is 0")
    @Max(value=50, message="Maximum number of teachers in a department is 50")
    private Integer teachers; 


    public Department() {
    }

    public Department(String code, String name, Integer teachers) {
        this.code = code;
        this.name = name;
        this.teachers = teachers;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getTeachers() {
        return this.teachers;
    }

    public void setTeachers(Integer teachers) {
        this.teachers = teachers;
    }

    @Override
    public String toString() {
        return "{" +
            " code='" + getCode() + "'" +
            ", name='" + getName() + "'" +
            ", teachers='" + getTeachers() + "'" +
            "}";
    }

}
