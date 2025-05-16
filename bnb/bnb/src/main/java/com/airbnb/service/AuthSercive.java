package com.airbnb.service;

import com.airbnb.payload.AppUserDto;
import com.airbnb.payload.LoginDto;

import java.util.Optional;

public interface AuthSercive {
    public AppUserDto addAppUser(AppUserDto appUserDto);

    public boolean verifyLogin(LoginDto loginDto);
}
