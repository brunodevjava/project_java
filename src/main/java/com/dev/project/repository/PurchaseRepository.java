package com.dev.project.repository;

import com.dev.project.domain.Product;
import com.dev.project.domain.Purchase;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchaseRepository extends CrudRepository<Purchase, Long> {


}
