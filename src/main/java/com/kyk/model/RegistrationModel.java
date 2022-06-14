package com.kyk.model;

import com.kyk.domain.Registration;

import java.util.List;

public interface RegistrationModel {

    List<Registration> findByClassModel(int id);

    void create(Registration registration);

}
