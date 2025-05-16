package com.airbnb.service;

import com.airbnb.entity.AppUser;
import com.airbnb.exception.UserExistsException;
import com.airbnb.payload.AppUserDto;
import com.airbnb.payload.LoginDto;
import com.airbnb.repository.AppUserRepository;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthSercive{
    private AppUserRepository aur;
    private JWTService jwtService;
    public AuthServiceImpl(AppUserRepository aur, JWTService jwtService) {
        this.aur = aur;
        this.jwtService = jwtService;
    }

    @Override
    public AppUserDto addAppUser(AppUserDto appUserDto) {
        Optional<AppUser> byEmail = aur.findByEmail(appUserDto.getEmail());
        Optional<AppUser> opUsername = aur.findByUsername(appUserDto.getUsername());
        if (byEmail.isPresent()) {
            throw new UserExistsException("email id already exists");
        }
        else if (opUsername.isPresent()) {
            throw new UserExistsException("Username already exists");
        }else{
            AppUser appUser = mapToEntity(appUserDto);
            aur.save(appUser);
            AppUserDto savedEntity = mapToDto(appUser);
            savedEntity.setMessage("App User is saved");
            return savedEntity;
        }
    }

    @Override
    public String verifyLogin(LoginDto loginDto) {
        Optional<AppUser> opUsername = aur.findByEmailOrUsername(loginDto.getUsername(),loginDto.getUsername());
        if (opUsername.isPresent()) {
            AppUser appUser = opUsername.get();
            if(BCrypt.checkpw(loginDto.getPassword(), appUser.getPassword())){
                return jwtService.generateToken(appUser);
            }
        }
        return null;
    }

    private AppUserDto mapToDto(AppUser appUser) {
        AppUserDto dto = new AppUserDto();
        dto.setId(appUser.getId());
        dto.setName(appUser.getName());
        dto.setEmail(appUser.getEmail());
        dto.setUsername(appUser.getUsername());
        dto.setPassword(appUser.getPassword());
        dto.setMessage("Saved AppUser");
        return dto;
    }

    private AppUser mapToEntity(AppUserDto dto) {
        AppUser appUser = new AppUser();
        appUser.setName(dto.getName());
        appUser.setEmail(dto.getEmail());
        appUser.setUsername(dto.getUsername());
        appUser.setPassword(BCrypt.hashpw(dto.getPassword(),BCrypt.gensalt(10)));
        return appUser;
    }
}
