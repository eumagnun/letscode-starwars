package br.com.danielamaral.starswarssocialnetwork.controller;

import br.com.danielamaral.starswarssocialnetwork.dto.DefaultResponse;
import br.com.danielamaral.starswarssocialnetwork.dto.LocationDto;
import br.com.danielamaral.starswarssocialnetwork.repository.LocationRepository;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
@Schema(name = "Location")
public class LocationController {

    @Autowired
    LocationRepository locationRepository;

    @GetMapping(value = "/locations", produces = "application/json")
    public DefaultResponse list() {
        DefaultResponse defaultResponse = new DefaultResponse();

        try {
            List<LocationDto> locationsDto = new ArrayList<>();
            locationRepository
                    .findAll()
                    .forEach(item -> locationsDto.add(LocationDto.parseLocationDto(item)));

            defaultResponse.setStatus(HttpStatus.OK.value());
            defaultResponse.setData(locationsDto);

        } catch (Exception ex) {
            defaultResponse.setData(ex.getMessage());
            defaultResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
        return defaultResponse;
    }
}
