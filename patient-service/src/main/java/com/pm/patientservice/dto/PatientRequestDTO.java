package com.pm.patientservice.dto;

import com.pm.patientservice.dto.validators.CreatePatientValidationGroup;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class PatientRequestDTO {

    @NotBlank ( message =  "Name is required")
    @Size ( max =  100 , message = "Name cannot exceed 100 characters ")
    private String name ;


    @NotBlank ( message =  "Email is required")
    @Email ( message = "Email should be valid")
    private  String email ;




    @NotBlank ( message =  "address is required")
    private  String address ;

    @NotBlank ( message =  "Date of birth is required")
    private  String date_of_birth ;

    @NotBlank(groups = CreatePatientValidationGroup.class, message = "Registered date is required ")
    private String registered_date ;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDate_of_birth() {
        return date_of_birth;
    }

    public void setDate_of_birth(String date_of_birth) {
        this.date_of_birth = date_of_birth;
    }

    public String getRegistered_date() {
        return registered_date;
    }

    public void setRegistered_date(String registered_date) {
        this.registered_date = registered_date;
    }
}
