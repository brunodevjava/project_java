package com.dev.project.repository;

import com.dev.project.domain.Purchase;
import com.dev.project.domain.Sale;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SaleRepository extends CrudRepository<Sale, Long> {


}
