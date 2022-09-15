package com.dev.project.service;

import com.dev.project.convertions.AbstractConversion;
import com.dev.project.domain.*;
import com.dev.project.dto.ProductTO;
import com.dev.project.dto.PurchaseTO;
import com.dev.project.exception.ResourceNotFoundException;
import com.dev.project.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Service
public class PurchaseService {

    @Autowired
    private AbstractConversion conversion;

    @Autowired
    private PurchaseRepository repository;
    @Autowired
    private DataRepository dataRepository;
    @Autowired
    private CfopRepository cfopRepository;
    @Autowired
    private ProductRepository productRepository;

    public PurchaseTO save(PurchaseTO dto, HttpServletRequest request) {
        Purchase purchase = (Purchase) conversion.convert(dto, new Purchase());

        Data data = dataRepository.findById(dto.getDataId()).orElseThrow(() -> new ResourceNotFoundException("Data not found."));
        purchase.setData(data);

        Cfop cfop = cfopRepository.findById(dto.getCfopId()).orElseThrow(() -> new ResourceNotFoundException("Cfop not found."));
        purchase.setCfop(cfop);

        Product product = productRepository.findById(dto.getProductId()).orElseThrow(() -> new ResourceNotFoundException("Product not found."));
        purchase.setProduct(product);

        PurchaseTO response = (PurchaseTO) conversion.convert(repository.save(purchase), new PurchaseTO());
        return response;
    }

    public void delete(Long id, HttpServletRequest request) {
        repository.deleteById(id);
    }


    public List<PurchaseTO> getAll() {
        return (List<PurchaseTO>) conversion.convertList(repository.findAll(), new ArrayList<PurchaseTO>(), "com.dev.project.dto.PurchaseTO");
    }

    public PurchaseTO getById(Long id) {
        return (PurchaseTO) conversion.convert(repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("PurchaseTO not found.")), new PurchaseTO());
    }

    public PurchaseTO update(PurchaseTO dto, HttpServletRequest request) {
        Purchase purchase = repository.findById(dto.getId()).orElseThrow(() -> new ResourceNotFoundException("PurchaseTO not found."));
        purchase = (Purchase) conversion.convert(dto, purchase);

        return (PurchaseTO) conversion.convert(repository.save(purchase), new PurchaseTO());
    }


}
