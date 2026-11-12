package com.pm.patientservice.service;

import com.pm.patientservice.dto.PatientRequestDTO;
import com.pm.patientservice.dto.PatientResponseDTO;
import com.pm.patientservice.mapper.PatientMapper;
import com.pm.patientservice.model.Patient;
import com.pm.patientservice.repository.PatientRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PatientService {


    private PatientRepository patientRepository;

    public PatientService ( PatientRepository patientRepository){
        // this is called dependency injection , instead of creating it outright ,
//        we create when we needed ( constructer ) is called . while earlier used to do like
        // new keyword outright in top line above . core more moduler and easier to test as we can pass mock
        // and it also adhere priciple of IOC ( inversion of control ) .
        //
       this.patientRepository = patientRepository;

    }

    public  List<PatientResponseDTO> getPatients ( ){


        List<Patient> patients = patientRepository.findAll();


        // Level 1 : you do not need to intaniate also return direclty also below
//        List<PatientResponseDTO> patientResponseDTOS = patients.
//                stream()
//                .map(patient -> PatientMapper.toDto(patient))
//                .toList();
//        return patientResponseDTOS;



        // Level: 2  this is better below is much better ->>
//      return patients.stream().map(patient -> PatientMapper.toDto(patient)).toList();


        // Level 3 : much shorter .
        return patients.stream().map(PatientMapper::toDto).toList();

    }

    public  PatientResponseDTO createPatient( PatientRequestDTO patientRequestDTO ){



        Patient newPatient =  patientRepository.save(PatientMapper.toModel(patientRequestDTO));


        return PatientMapper.toDto(newPatient);
    }

}
