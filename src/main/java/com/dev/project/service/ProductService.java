package com.dev.project.service;

import com.dev.project.convertions.AbstractConversion;
import com.dev.project.domain.Product;
import com.dev.project.dto.ProductTO;
import com.dev.project.exception.ResourceNotFoundException;
import com.dev.project.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    private AbstractConversion conversion;

    @Autowired
    private ProductRepository repository;
    @Autowired
    private NcmRepository ncmRepository;

    public ProductTO save(ProductTO dto, HttpServletRequest request) {
        Product product = (Product) conversion.convert(dto, new Product());

        Ncm nmc = ncmRepository.findById(dto.getNcmId()).orElseThrow(() -> new ResourceNotFoundException("Ncm not found."));
        product.setNcm(nmc);

        ProductTO response = (ProductTO) conversion.convert(repository.save(product), new ProductTO());
        return response;
    }

    public void delete(Long id, HttpServletRequest request) {
        repository.deleteById(id);
    }


    public List<ProductTO> getAll() {
        return (List<ProductTO>) conversion.convertList(repository.findAll(), new ArrayList<ProductTO>(), "com.dev.project.dto.ProductTO");
    }

    public ProductTO getById(Long id) {
        return (ProductTO) conversion.convert(repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product not found.")), new ProductTO());
    }

    public ProductTO update(ProductTO dto, HttpServletRequest request) {
        Product product = repository.findById(dto.getId()).orElseThrow(() -> new ResourceNotFoundException("Product not found."));
        product = (Product) conversion.convert(dto, product);

        return (ProductTO) conversion.convert(repository.save(product), new ProductTO());
    }


}
