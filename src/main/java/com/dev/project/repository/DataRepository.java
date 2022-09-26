package com.dev.project.repository;

import com.dev.project.domain.Data;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DataRepository extends CrudRepository<Data, Long> {

//alteracao x
}
