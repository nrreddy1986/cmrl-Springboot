package com.shellinfo.demo.controller;

import com.shellinfo.demo.model.Station;
import com.shellinfo.demo.repository.StationRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/internal/stations")
public class StationAdminController {

    private final StationRepository repository;

    public StationAdminController(StationRepository repository) {
        this.repository = repository;
    }

    @PostMapping("/save-all")
    public String saveAll(@RequestBody List<Station> stations) {
        repository.saveAll(stations);
        return "Stations inserted successfully";
    }
}