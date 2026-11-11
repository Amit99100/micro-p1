package com.pm.patientservice.controller;


import com.pm.patientservice.dto.PatientRequestDTO;
import com.pm.patientservice.dto.PatientResponseDTO;
import com.pm.patientservice.repository.PatientRepository;
import com.pm.patientservice.service.PatientService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/patients") // http://localhost:4000/patients
public class PatientController {


    @Autowired
    private PatientService patientService;


    public PatientController(PatientService patientService) {
    this.patientService = patientService;
    }

  // ResponseEntity is the spring object that creates the http
    // response
    @GetMapping
    public ResponseEntity <List<PatientResponseDTO>>  getPatients(){
        List<PatientResponseDTO> patientDTOs = patientService.getPatients();
        return ResponseEntity.ok().body(patientDTOs);
    }

    @PostMapping
    public ResponseEntity  < PatientResponseDTO> createPatient(
            @Valid
            @RequestBody PatientRequestDTO patientRequestDTO){

        System.out.println("Here i reached ");

         PatientResponseDTO patientResponseDTO = patientService.createPatient(patientRequestDTO);

        return ResponseEntity.ok().body(patientResponseDTO);
    }


    @PutMapping ( "/{id}")
    //localhost:4000/patients/12343434343
    public ResponseEntity < PatientRequestDTO> updatePatient ( ){





    }

}
