package com.dev.project.service;

import com.dev.project.convertions.AbstractConversion;
import com.dev.project.domain.Address;
import com.dev.project.domain.Data;
import com.dev.project.dto.AddressTO;
import com.dev.project.dto.DataTO;
import com.dev.project.exception.ResourceNotFoundException;
import com.dev.project.repository.AddressRepository;
import com.dev.project.repository.DataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Service
public class AddressService {

    @Autowired
    private AbstractConversion conversion;

    @Autowired
    private AddressRepository repository;
    public AddressTO save(AddressTO dto, HttpServletRequest request) {
        Address address = (Address) conversion.convert(dto, new Address());
        AddressTO response = (AddressTO) conversion.convert(repository.save(address), new AddressTO());
        return response;
    }
    public void delete(Long id, HttpServletRequest request) {
        repository.deleteById(id);
    }


    public List<AddressTO> getAll() {
        return (List<AddressTO>) conversion.convertList(repository.findAll(), new ArrayList<AddressTO>(), "com.dev.project.dto.AddressTO");
    }

    public AddressTO getById(Long id) {
        return (AddressTO) conversion.convert(repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Address not found.")), new AddressTO());
    }
    public AddressTO update(AddressTO dto, HttpServletRequest request) {
        Address address = repository.findById(dto.getId()).orElseThrow(() -> new ResourceNotFoundException("Address not found."));
        address = (Address) conversion.convert(dto, address);

        return (AddressTO) conversion.convert(repository.save(address), new AddressTO());
    }




}
