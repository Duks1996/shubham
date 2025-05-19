package com.airbnb.controller;

import com.airbnb.entity.City;
import com.airbnb.entity.Country;
import com.airbnb.service.CountryCityService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/countrycity")
public class CountryCityController {
    private CountryCityService countryCityService;

    public CountryCityController(CountryCityService countryCityService) {
        this.countryCityService = countryCityService;
    }

    @PostMapping("/addcountry")
    public ResponseEntity<Country> addCountry(@RequestBody Country c){
        Country savedCountry = countryCityService.addCountry(c);
        return new ResponseEntity<>(savedCountry, HttpStatus.CREATED);
    }

    @PostMapping("/addcity")
    public ResponseEntity<City> addCity(@RequestBody City c){
        City savedCity = countryCityService.addCity(c);
        return new ResponseEntity<>(savedCity, HttpStatus.CREATED);
    }

    @DeleteMapping("/deleteCountry")
    public ResponseEntity<String> deleteCountry(@RequestParam Long id){
        if(countryCityService.deleteCountry(id)){
            return new ResponseEntity<>("Country deleted",HttpStatus.OK);
        }else{
            return new ResponseEntity<>("Bad request to delete country",HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/deleteCity")
    public ResponseEntity<String> deleteCity(@RequestParam Long id){
        if(countryCityService.deleteCity(id)){
            return new ResponseEntity<>("City deleted",HttpStatus.OK);
        }else{
            return new ResponseEntity<>("Bad request to delete city",HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/countries")
    public ResponseEntity<List<Country>> getAllCountries() {
        return new ResponseEntity<>(countryCityService.getAllCountries(), HttpStatus.OK);
    }

    @GetMapping("/cities")
    public ResponseEntity<List<City>> getAllCities() {
        return new ResponseEntity<>(countryCityService.getAllCities(), HttpStatus.OK);
    }

    @PutMapping("/updatecountry")
    public ResponseEntity<Country> updateCountry(@RequestParam Long id,@RequestBody Country country) {
        Country updated = countryCityService.updateCountry(id,country);
        return updated != null ?
                new ResponseEntity<>(updated, HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/updatecity")
    public ResponseEntity<City> updateCity(@RequestParam Long id,@RequestBody City city) {
        City updated = countryCityService.updateCity(id,city);
        return updated != null ?
                new ResponseEntity<>(updated, HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
