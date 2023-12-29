package com.logfinder.logfinder.service;

import com.logfinder.logfinder.entitie.LogFileEntity;
import com.logfinder.logfinder.repositorie.LogFileEntityRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LogFileEntityServiceImpl implements LogFileEntityService{


    private LogFileEntityRepo logFileEntityRepo;

    @Autowired
    public LogFileEntityServiceImpl(LogFileEntityRepo logFileEntityRepo) {
        this.logFileEntityRepo = logFileEntityRepo;
    }

    @Override
    public LogFileEntity createLogFile(LogFileEntity logFileEntity) {
        return logFileEntityRepo.save(logFileEntity);
    }

    @Override
    public LogFileEntity updateLogFile(LogFileEntity logFileEntity) {
        return logFileEntityRepo.save(logFileEntity);
    }

    @Override
    public Optional<LogFileEntity> getLogFileEntity(Long lid) {
        return logFileEntityRepo.findById( lid);
    }

    @Override
    public List<LogFileEntity> getAllFiles() {
        return logFileEntityRepo.findAll();
    }

    @Override
    public void deleteLogFileEntity(LogFileEntity logFileEntity) {
        logFileEntityRepo.delete(logFileEntity);

    }

    @Override
    public void deleteById(Long lid) {
        logFileEntityRepo.deleteById(lid);

    }
}
