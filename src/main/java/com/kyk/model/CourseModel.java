package com.kyk.model;

import com.kyk.domain.Course;

import java.util.List;

public interface CourseModel {

    List<Course> getAll();
    void save(Course course);

    Course findById(int id);

}
