package com.apiexample.service;

import com.apiexample.entity.Registration;
import com.apiexample.payload.RegDto;
import com.apiexample.payload.RegistrationDto;
import com.apiexample.repository.RegistrationRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RegistrationServiceImpl implements RegistrationService{
    private RegistrationRepository rr;

    public RegistrationServiceImpl(RegistrationRepository rr){
        this.rr=rr;
    }

    @Override
    public RegistrationDto createRegistration(RegistrationDto rDto) {
        Registration r = mapToEntity(rDto);
        Registration savedEntity = rr.save(r);
        RegistrationDto savedDto = mapToDto(savedEntity);
        savedDto.setMessage("Registration is saved!!!");
        return savedDto;
    }
    @Override
    public void deleteRegistrationById(Long id){
        rr.deleteById(id);
    }
    @Override
    public RegistrationDto updateRegistration(Long id,RegistrationDto rDto){
        Optional<Registration> byId = rr.findById(id);
        Registration r = byId.get();
        r.setName(rDto.getName());
        r.setEmail(rDto.getEmail());
        r.setMobile(rDto.getMobile());
        Registration savedEntity = rr.save(r);
        RegistrationDto savedDto = mapToDto(savedEntity);
        savedDto.setMessage("Registration is updated!!!");
        return savedDto;
    }
    @Override
    public RegDto getAllRegistrations(int pageNo, int pageSize, String sortBy, String sorDir){
        Sort.Direction dir = Sort.Direction.fromString(sorDir);
        Sort sort = Sort.by(dir,sortBy);

        Pageable pageable = PageRequest.of(pageNo,pageSize,sort);
        Page<Registration> page = rr.findAll(pageable);

        List<Registration> all = page.getContent();
        List<RegistrationDto> collect = all.stream().map(e -> mapToDto(e)).collect(Collectors.toList());

        RegDto regDto = new RegDto();
        regDto.setCollect(collect);
        regDto.setPageNo(pageable.getPageNumber());
        regDto.setPageSize(pageable.getPageSize());
        regDto.setTotalPages(page.getTotalPages());
        regDto.setFirstPage(page.isFirst());
        regDto.setLastPage(page.isLast());


        return regDto;
    }

    @Override
    public RegistrationDto getRegistrationById(Long id) {
        Registration registration = rr.findById(id).orElseThrow(() -> new RuntimeException("Registration not found with id: " + id));
        RegistrationDto registrationDto = mapToDto(registration);
        return registrationDto;
    }

    private Registration mapToEntity(RegistrationDto rDto){
        Registration r = new Registration();
        r.setName(rDto.getName());
        r.setEmail(rDto.getEmail());
        r.setMobile(rDto.getMobile());
        return r;
    }
    private RegistrationDto mapToDto(Registration r){
        RegistrationDto rDto = new RegistrationDto();
        rDto.setId(r.getId());
        rDto.setName(r.getName());
        rDto.setEmail(r.getEmail());
        rDto.setMobile(r.getMobile());
        rDto.setMessage("Saved Registration");
        return rDto;
    }
}
