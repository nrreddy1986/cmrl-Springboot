package com.shellinfo.demo.controller;

import com.shellinfo.demo.model.Line;
import com.shellinfo.demo.repository.LineRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/internal/lines")
public class LineAdminController {

    private final LineRepository repository;

    public LineAdminController(LineRepository repository) {
        this.repository = repository;
    }

    @PostMapping("/save-all")
    public String saveAll(@RequestBody List<Line> lines) {
        repository.saveAll(lines);
        return "Lines inserted successfully";
    }
}