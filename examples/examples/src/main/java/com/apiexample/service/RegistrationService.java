package com.apiexample.service;

import com.apiexample.payload.RegDto;
import com.apiexample.payload.RegistrationDto;

import java.util.List;

public interface RegistrationService {
    public RegistrationDto createRegistration(RegistrationDto rDto);
    public void deleteRegistrationById(Long id);
    public RegistrationDto updateRegistration(Long id,RegistrationDto rDto);
    public RegDto getAllRegistrations(int pageNo, int pageSize, String sortBy, String sorDir);
    public RegistrationDto getRegistrationById(Long id);
}
