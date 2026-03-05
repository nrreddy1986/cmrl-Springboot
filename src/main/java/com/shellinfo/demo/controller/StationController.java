package com.shellinfo.demo.controller;

import com.shellinfo.demo.model.ApiResponse;
import com.shellinfo.demo.model.Station;
import com.shellinfo.demo.repository.StationRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/stations")
public class StationController {

    private final StationRepository repository;

    public StationController(StationRepository repository) {
        this.repository = repository;
    }

    /*// GET all stations
    @GetMapping
    public List<Station> getAllStations() {
        return repository.findAll();
    }

    // GET by lineId
    @GetMapping("/by-line")
    public List<Station> getByLineId(@RequestParam Integer lineId) {
        return repository.findByLineId(lineId);
    }*/

    @GetMapping
    public ResponseEntity<ApiResponse<List<Station>>> getStations(
            @RequestParam(required = false) Integer lineId,
            @RequestParam(required = false) String stationUniqueId) {

        List<Station> stations = new ArrayList<>();

        if (lineId != null) {
            stations = repository.findByLineId(lineId);
        } else if (stationUniqueId != null) {
            stations = repository.findByStationUniqueId(stationUniqueId);
        } else {
            stations = repository.findAll();
        }

        return ResponseEntity.ok(
                ApiResponse.success((long) stations.size() + " Stations Available", stations));
    }
}
