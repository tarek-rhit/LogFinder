package com.logfinder.logfinder.entitie;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name="logFile")
public class LogFileEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private  String name;
    @Column
    private String path;
    @Column
    private Date date;


    public LogFileEntity() {
    }

    public LogFileEntity(String name, String path, Date date) {
        this.name = name;
        this.path = path;
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "LogFileEntity{" +
                "name='" + name + '\'' +
                ", path='" + path + '\'' +
                ", date=" + date +
                '}';
    }
}
