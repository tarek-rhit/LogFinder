package com.logfinder.logfinder.controller;


import com.logfinder.logfinder.entitie.LogFileEntity;
import com.logfinder.logfinder.model.LogFile;

import com.logfinder.logfinder.service.LogFileEntityService;
import com.logfinder.logfinder.service.LogFileEntityServiceImpl;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/v1/logs")
public class LogFileController implements LogFileEntityApi {


    private LogFileEntityService logFileEntityService;

    @Autowired
    public LogFileController(LogFileEntityService logFileEntityService) {
        this.logFileEntityService = logFileEntityService;
    }

    Logger logger = LoggerFactory.getLogger(LogFileController.class);

    @Value("${path.log}")
    private String logFinderPath;

    @GetMapping("/all")
    public List<LogFile> allLogFiles () throws IOException {
        logger.debug("Call allLogFiles method");
        return getLogFinder();

    }

    @RequestMapping(path="/{logName}", method = RequestMethod.GET,produces = "application/zip")
    public byte[] findByLog(HttpServletResponse response, @PathVariable String logName) throws IOException {
        logger.debug("CAll allLogFiles method");
        File theFile = returnTheGoodFile(logName);
        return getBytesFromFile(theFile);

    }

    public List<LogFile> getLogFinder() throws IOException {
        final List<LogFile> list = new ArrayList<>();
        try(final DirectoryStream<Path> directoryStream = Files.newDirectoryStream(Paths.get(logFinderPath),path->path.toString().contains(".log"))){
            for (final Path p:directoryStream) {
                final LogFile logFinder = new LogFile();
                logFinder.setName(p.getFileName().toString());
                logFinder.setPath(p.toAbsolutePath().toString());
                logFinder.setDate(new Date(p.toFile().lastModified()));

                list.add(logFinder);
            }
            return list;
        }
    }

    public File returnTheGoodFile(String fileName) throws IOException {
        File theSearchFile = null;
        try(final DirectoryStream<Path> directoryStream = Files.newDirectoryStream(Paths.get(logFinderPath),path->path.toString().contains(".log"))){
            for (final Path p:directoryStream) {
                theSearchFile=p.toFile();
            }
        }
        return theSearchFile;
    }

    public byte[] getBytesFromFile(File file) throws IOException{
        long length = file.length();
        if(length>Integer.MAX_VALUE){
            throw new IOException("Le fichier est trop grand !");
        }
        byte[] bytes = new byte[(int)length];
        int offset=0;
        int numRead=0;

        InputStream is = new FileInputStream(file);
        try{
            while(offset<bytes.length && (numRead=is.read(bytes,offset,bytes.length))- offset >=0){
                offset+=numRead;
              }
            }finally{
                is.close();
            }
        if(offset<bytes.length){
            throw new IOException("Le fichier n'est pas complet"+file.getName());
        }
        return bytes;
        }


    @Override
    public LogFileEntity createLogFile(LogFileEntity logFileEntity) {
        return logFileEntityService.createLogFile(logFileEntity);
    }

    @Override
    public LogFileEntity updateLogFile(LogFileEntity logFileEntity) {
        return logFileEntityService.updateLogFile(logFileEntity);
    }

    @Override
    public Optional<LogFileEntity> getLogFileEntity(Long lid) {
        return logFileEntityService.getLogFileEntity(lid);
    }

    @Override
    public List<LogFileEntity> getAllFiles() {
        return logFileEntityService.getAllFiles();
    }

    @Override
    public void deleteLogFileEntity(LogFileEntity logFileEntity) {
        logFileEntityService.deleteLogFileEntity(logFileEntity);

    }

    @Override
    public void deleteById(Long lid) {
        logFileEntityService.deleteById(lid);
    }
}
