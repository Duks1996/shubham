package com.apiexample.controller;

import com.apiexample.payload.RegDto;
import com.apiexample.payload.RegistrationDto;
import com.apiexample.service.RegistrationService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/registration")
public class RegistrationController {
    private RegistrationService rs;
    public RegistrationController(RegistrationService rs){
        this.rs=rs;
    }
    //http://localhost:8080/api/v1/registration
    @PostMapping
    public ResponseEntity<?> addRegistration(
            @Valid @RequestBody RegistrationDto rDto,
            BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return new ResponseEntity<>(bindingResult.getFieldError().getDefaultMessage(), HttpStatus.BAD_REQUEST);
        }else {
            RegistrationDto rgDto = rs.createRegistration(rDto);
            return new ResponseEntity<>(rgDto, HttpStatus.CREATED);
        }
    }
    //http://localhost:8080/api/v1/registration?id=12
    @DeleteMapping
    public ResponseEntity<String> deleteRegistration(@RequestParam Long id){
        rs.deleteRegistrationById(id);
        return new ResponseEntity<>("Registration is deleted!!!",HttpStatus.OK);
    }
    //http://localhost:8080/api/v1/registration?id=12
    @PutMapping
    public ResponseEntity<?> updateRegistration(
            @RequestParam Long id,
            @Valid @RequestBody RegistrationDto rDto,
            BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return new ResponseEntity<>(bindingResult.getFieldError().getDefaultMessage(), HttpStatus.BAD_REQUEST);
        }else{
            RegistrationDto rgDto = rs.updateRegistration(id,rDto);
            return new ResponseEntity<>(rgDto, HttpStatus.OK);
        }
    }

    //http://localhost:8080/api/v1/registration?pageNo=0&pageSize=3
    @GetMapping
    public ResponseEntity<RegDto> getAllRegistrations(
            @RequestParam (name="pageNo", defaultValue="0", required = false) int pageNo,
            @RequestParam (name="pageSize", defaultValue="5", required = false) int pageSize,
            @RequestParam (name="sortBy", defaultValue="name", required = false) String sortBy,
            @RequestParam (name="sorDir", defaultValue="AsC", required = false) String sorDir
    ){
        RegDto all = rs.getAllRegistrations(pageNo,pageSize,sortBy,sorDir);
        return new ResponseEntity<>(all, HttpStatus.OK);
    }

    //http://localhost:8080/api/v1/registration?id=12
    @GetMapping("/byid")
    public ResponseEntity<RegistrationDto> getRegistrationById(@RequestParam Long id){
        RegistrationDto rgDto = rs.getRegistrationById(id);
        return new ResponseEntity<>(rgDto, HttpStatus.OK);
    }
}
