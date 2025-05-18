package com.airbnb.controller;

import com.airbnb.payload.AppUserDto;
import com.airbnb.payload.JWTToken;
import com.airbnb.payload.LoginDto;
import com.airbnb.service.AuthSercive;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    private AuthSercive as;

    public AuthController(AuthSercive as) {
        this.as = as;
    }
    //http://localhost:8080/api/v1/auth

    @PostMapping("/createappuser")
    public ResponseEntity<?> createAppUser(@RequestBody @Valid AppUserDto appUserDto, BindingResult res){
        if(res.hasErrors()){
            return new ResponseEntity<>(res.getFieldError().getDefaultMessage(), HttpStatus.BAD_REQUEST);
        }else{
            appUserDto.setRole("ROLE_USER");
            AppUserDto savedAppUser = as.addAppUser(appUserDto);
            return new ResponseEntity<>(savedAppUser, HttpStatus.CREATED);
        }
    }

    @PostMapping("/createpropertyowner")
    public ResponseEntity<?> createPropertyOwner(@RequestBody @Valid AppUserDto appUserDto, BindingResult res){
        if(res.hasErrors()){
            return new ResponseEntity<>(res.getFieldError().getDefaultMessage(), HttpStatus.BAD_REQUEST);
        }else{
            appUserDto.setRole("ROLE_OWNER");
            AppUserDto savedAppUser = as.addAppUser(appUserDto);
            return new ResponseEntity<>(savedAppUser, HttpStatus.CREATED);
        }
    }

    @PostMapping("/createpropertymanager")
    public ResponseEntity<?> createPropertyManager(@RequestBody @Valid AppUserDto appUserDto, BindingResult res){
        if(res.hasErrors()){
            return new ResponseEntity<>(res.getFieldError().getDefaultMessage(), HttpStatus.BAD_REQUEST);
        }else{
            appUserDto.setRole("ROLE_MANAGER");
            AppUserDto savedAppUser = as.addAppUser(appUserDto);
            return new ResponseEntity<>(savedAppUser, HttpStatus.CREATED);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> signIn(@RequestBody LoginDto loginDto){
        if(as.verifyLogin(loginDto)!=null){
            JWTToken jwtToken = new JWTToken();
            jwtToken.setTokenType("JWT");
            jwtToken.setToken(as.verifyLogin(loginDto));
            return new ResponseEntity<>(jwtToken,HttpStatus.OK);
        }else {
            return new ResponseEntity<>("Invalid Username/Password",HttpStatus.UNAUTHORIZED);
        }
    }

}
