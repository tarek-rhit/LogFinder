package com.logfinder.logfinder.repositorie;

import com.logfinder.logfinder.entitie.LogFileEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LogFileEntityRepo extends JpaRepository<LogFileEntity,Long> {


}
