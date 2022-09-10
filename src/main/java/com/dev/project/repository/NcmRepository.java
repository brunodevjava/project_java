package com.dev.project.repository;

import com.dev.project.domain.Data;
import com.dev.project.domain.Ncm;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NcmRepository extends CrudRepository<Ncm, Long> {


}
