package com.dev.project.service;

import com.dev.project.domain.Address;
import com.dev.project.domain.Data;
import com.dev.project.dto.AddressTO;
import com.dev.project.exception.ResourceNotFoundException;
import com.dev.project.repository.AddressRepository;
import com.dev.project.repository.DataRepository;
import com.dev.project.convertions.AbstractConversion;
import com.dev.project.dto.DataTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Service
public class DataService {

    @Autowired
    private AbstractConversion conversion;

    @Autowired
    private DataRepository repository;
    @Autowired
    private AddressRepository addressRepository;

    public DataTO save(DataTO dto, HttpServletRequest request) {
        Data data = (Data) conversion.convert(dto, new Data());
        System.out.println(dto);
        Address adt = addressRepository.findById(dto.getAddressId()).orElseThrow(() -> new ResourceNotFoundException("Addres not found."));
        data.setAddress(adt);
        DataTO response = (DataTO) conversion.convert(repository.save(data), new DataTO());
        response.setAddressId(adt.getId());
        return response;
    }

    public void delete(Long id, HttpServletRequest request) {
        repository.deleteById(id);
    }


    public List<DataTO> getAll() {
        return (List<DataTO>) conversion.convertList(repository.findAll(), new ArrayList<DataTO>(), "com.dev.project.dto.DataTO");
    }

    public DataTO getById(Long id) {
        return (DataTO) conversion.convert(repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found.")), new DataTO());
    }

    public DataTO update(DataTO dto, HttpServletRequest request) {
        Data data = repository.findById(dto.getId()).orElseThrow(() -> new ResourceNotFoundException("Data not found."));
        data = (Data) conversion.convert(dto, data);

        return (DataTO) conversion.convert(repository.save(data), new DataTO());
    }


}
