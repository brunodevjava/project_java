package com.dev.project.service;

import com.dev.project.convertions.AbstractConversion;
import com.dev.project.domain.*;
import com.dev.project.dto.PurchaseTO;
import com.dev.project.dto.SaleTO;
import com.dev.project.exception.ResourceNotFoundException;
import com.dev.project.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Service
public class SaleService {

    @Autowired
    private AbstractConversion conversion;

    @Autowired
    private SaleRepository repository;
    @Autowired
    private DataRepository dataRepository;
    @Autowired
    private CfopRepository cfopRepository;
    @Autowired
    private ProductRepository productRepository;

    public SaleTO save(SaleTO dto, HttpServletRequest request) {
        Sale sale = (Sale) conversion.convert(dto, new Sale());

        Data data = dataRepository.findById(dto.getDataId()).orElseThrow(() -> new ResourceNotFoundException("Data not found."));
        sale.setData(data);

        Cfop cfop = cfopRepository.findById(dto.getCfopId()).orElseThrow(() -> new ResourceNotFoundException("Cfop not found."));
        sale.setCfop(cfop);

        Product product = productRepository.findById(dto.getProductId()).orElseThrow(() -> new ResourceNotFoundException("Product not found."));
        sale.setProduct(product);

        SaleTO response = (SaleTO) conversion.convert(repository.save(sale), new SaleTO());
        return response;
    }

    public void delete(Long id, HttpServletRequest request) {
        repository.deleteById(id);
    }


    public List<SaleTO> getAll() {
        return (List<SaleTO>) conversion.convertList(repository.findAll(), new ArrayList<SaleTO>(), "com.dev.project.dto.SaleTO");
    }

    public SaleTO getById(Long id) {
        return (SaleTO) conversion.convert(repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("SaleTO not found.")), new SaleTO());
    }

    public SaleTO update(SaleTO dto, HttpServletRequest request) {
        Sale sale = repository.findById(dto.getId()).orElseThrow(() -> new ResourceNotFoundException("SaleTO not found."));
        sale = (Sale) conversion.convert(dto, sale);

        return (SaleTO) conversion.convert(repository.save(sale), new SaleTO());
    }


}
