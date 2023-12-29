package com.logfinder.logfinder.service;

import com.logfinder.logfinder.entitie.LogFileEntity;

import java.util.List;
import java.util.Optional;

public interface LogFileEntityService {


    LogFileEntity createLogFile(LogFileEntity logFileEntity);
    LogFileEntity updateLogFile(LogFileEntity logFileEntity);

    Optional<LogFileEntity> getLogFileEntity(Long lid);
    List<LogFileEntity> getAllFiles();

    void deleteLogFileEntity(LogFileEntity logFileEntity);
    void deleteById(Long lid);
}
