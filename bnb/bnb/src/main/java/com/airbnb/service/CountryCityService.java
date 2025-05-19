package com.airbnb.service;

import com.airbnb.entity.City;
import com.airbnb.entity.Country;

import java.util.List;

public interface CountryCityService {
    boolean deleteCountry(Long id);
    boolean deleteCity(Long id);

    Country addCountry(Country c);
    City addCity(City c);

    List<Country> getAllCountries();
    List<City> getAllCities();

    Country updateCountry(Long id,Country c);
    City updateCity(Long id,City c);
}
