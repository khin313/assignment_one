package com.kyk.model;

import com.kyk.domain.Course;
import com.kyk.domain.OpenClass;

import java.util.List;

public interface OpenClassModel {
    List<OpenClass> findByCourse(int id);

    void create(OpenClass openClass);

    OpenClass findById(int ocId);
}
