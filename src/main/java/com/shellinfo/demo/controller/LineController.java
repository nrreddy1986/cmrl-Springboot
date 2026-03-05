package com.shellinfo.demo.controller;

import com.shellinfo.demo.model.Line;
import com.shellinfo.demo.repository.LineRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/lines")
public class LineController {

    private final LineRepository repository;

    public LineController(LineRepository repository) {
        this.repository = repository;
    }

   /* // GET all Lines
    @GetMapping
    public List<Line> getAllLines() {
        return repository.findAll();
    }

    // GET Line by id
    @GetMapping("line-info")
    public List<Line> getByLineId(@RequestParam Integer lineId) {
        return repository.findByLineId(lineId);
    }*/

    // GET all Lines
    @GetMapping
    public List<Line> getLines(@RequestParam(required = false) Integer lineId) {
        if (lineId != null) {
            return repository.findByLineId(lineId);
        }
        return repository.findAll();
    }
}
