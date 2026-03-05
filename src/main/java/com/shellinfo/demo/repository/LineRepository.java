package com.shellinfo.demo.repository;

import com.shellinfo.demo.model.Line;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LineRepository extends JpaRepository<Line, Long> {
    List<Line> findByLineId(Integer lineId);
}