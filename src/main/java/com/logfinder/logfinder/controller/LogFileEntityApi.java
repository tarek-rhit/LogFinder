package com.logfinder.logfinder.controller;

import com.logfinder.logfinder.entitie.LogFileEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.List;
import java.util.Optional;

public interface LogFileEntityApi {

    @PostMapping("/creer")
    LogFileEntity createLogFile(LogFileEntity logFileEntity);
    @PutMapping("/modifier")
    LogFileEntity updateLogFile(LogFileEntity logFileEntity);

    @GetMapping("/afficher/{lid]")
    Optional<LogFileEntity> getLogFileEntity(Long lid);
    @GetMapping("/all")
    List<LogFileEntity> getAllFiles();

    @DeleteMapping("/supprimer")
    void deleteLogFileEntity(LogFileEntity logFileEntity);
    @DeleteMapping("/supprimer/{lid]")
    void deleteById(Long lid);
}
