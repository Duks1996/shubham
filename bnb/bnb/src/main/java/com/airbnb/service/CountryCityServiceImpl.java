package com.airbnb.service;

import com.airbnb.entity.City;
import com.airbnb.entity.Country;
import com.airbnb.repository.CityRepository;
import com.airbnb.repository.CountryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CountryCityServiceImpl implements CountryCityService{
    private CountryRepository countryRepository;
    private CityRepository cityRepository;

    public CountryCityServiceImpl(CountryRepository countryRepository, CityRepository cityRepository) {
        this.countryRepository = countryRepository;
        this.cityRepository = cityRepository;
    }

    @Override
    public boolean deleteCountry(Long id) {
        if(countryRepository.existsById(id)) {
            countryRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteCity(Long id) {
        if(cityRepository.existsById(id)) {
            cityRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public Country addCountry(Country c) {
        return countryRepository.save(c);
    }

    @Override
    public City addCity(City c) {
        return cityRepository.save(c);
    }

    @Override
    public List<Country> getAllCountries() {
        return countryRepository.findAll();
    }

    @Override
    public List<City> getAllCities() {
        return cityRepository.findAll();
    }

    @Override
    public Country updateCountry(Long id,Country c) {
        Optional<Country> byId = countryRepository.findById(id);
        if (byId.isPresent()){
            Country country = byId.get();
            country.setName(c.getName());
            return countryRepository.save(country);
        }
        return null;
    }

    @Override
    public City updateCity(Long id,City c) {
        Optional<City> byId = cityRepository.findById(id);
        if (byId.isPresent()){
            City city = byId.get();
            city.setName(c.getName());
            return cityRepository.save(city);
        }
        return null;
    }
}
