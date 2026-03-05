package com.shellinfo.demo.repository;

import com.shellinfo.demo.model.Station;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StationRepository extends JpaRepository<Station, Long> {
    List<Station> findByLineId(Integer lineId);

    List<Station> findByStationUniqueId(String stationUniqueId);
}