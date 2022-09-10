package com.dev.project.repository;

import com.dev.project.domain.Address;
import com.dev.project.domain.Cfop;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CfopRepository extends CrudRepository<Cfop, Long> {


}
