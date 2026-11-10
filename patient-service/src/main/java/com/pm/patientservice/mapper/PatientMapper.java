package com.pm.patientservice.mapper;

import com.pm.patientservice.dto.PatientRequestDTO;
import com.pm.patientservice.dto.PatientResponseDTO;
import com.pm.patientservice.model.Patient;

import java.lang.reflect.ParameterizedType;
import java.time.LocalDate;

public class PatientMapper {


    public static PatientResponseDTO toDto(Patient patient) {
        PatientResponseDTO patientDTO = new PatientResponseDTO();

        patientDTO.setId(patient.getId().toString());
        patientDTO.setName(patient.getName());
        patientDTO.setAddress(patient.getAddress());
        patientDTO.setEmail( patient.getEmail());
        patientDTO.setDate_of_birth(patient.getDate_of_birth().toString());
        return patientDTO;

    }

    public static Patient toModel(PatientRequestDTO patientRequestDTO) {

       Patient patient = new Patient();

       patient.setName(patientRequestDTO.getName());
       patient.setAddress(patientRequestDTO.getAddress());
       patient.setEmail(patientRequestDTO.getEmail());
       patient.setDate_of_birth(LocalDate.parse(patientRequestDTO.getDate_of_birth()));
       patient.setRegistered_date(LocalDate.parse(patientRequestDTO.getRegistered_date()));
       return patient;

    }
}
