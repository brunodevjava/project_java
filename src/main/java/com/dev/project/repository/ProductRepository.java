package com.dev.project.repository;

import com.dev.project.domain.Ncm;
import com.dev.project.domain.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {


}
