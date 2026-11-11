package com.pm.patientservice.service;

import com.pm.patientservice.dto.PatientRequestDTO;
import com.pm.patientservice.dto.PatientResponseDTO;
import com.pm.patientservice.exception.EmailAlreadyExistsException;
import com.pm.patientservice.exception.PatientNotFoundException;
import com.pm.patientservice.mapper.PatientMapper;
import com.pm.patientservice.model.Patient;
import com.pm.patientservice.repository.PatientRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PatientService {


    private PatientRepository patientRepository;

    public PatientService ( PatientRepository patientRepository){
        // this is called dependency injection , instead of creating it outright ,
//        we create when we needed ( constructer ) is called . while earlier used to do like
        // new keyword outright in top line above . core more moduler and easier to test as we can pass mock
        // and it also adhere priciple of IOC ( inversion of control ) .



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

    public  PatientResponseDTO  getPatientById( UUID id ){
        Patient patient = patientRepository.findById(id).orElseThrow(()-> new PatientNotFoundException("Patient not found with ID: " + id ) );
         return (PatientMapper.toDto(patient));
    }

    public  PatientResponseDTO createPatient( PatientRequestDTO patientRequestDTO ){

        String Email = patientRequestDTO.getEmail();
        if( patientRepository.existsByEmail(Email ) ){

            throw new EmailAlreadyExistsException( "A patient with this email "
            + "already exits" + Email
            );

        }
        Patient newPatient =  patientRepository.save(PatientMapper.toModel(patientRequestDTO));


        return  PatientMapper.toDto(newPatient);
    }


    public PatientResponseDTO updatePatient(UUID id , PatientRequestDTO patientRequestDTO ){

        Patient patient = patientRepository.findById(id)
                .orElseThrow(()-> new PatientNotFoundException("Patient not found with ID: " + id )) ;


         if( patientRepository.existsByEmail(patientRequestDTO.getEmail() ) ){

             throw new EmailAlreadyExistsException(
                     "A patient with this email already exits" + patientRequestDTO.getEmail()
             );
         }
        patient.setName(patientRequestDTO.getName());
        patient.setEmail(patientRequestDTO.getEmail());
        patient.setEmail(patientRequestDTO.getEmail());
        patient.setDate_of_birth(LocalDate.parse(patientRequestDTO.getDate_of_birth()));

        Patient updatedPatient =  patientRepository.save(patient);

        return  PatientMapper.toDto(updatedPatient);
    };
}
