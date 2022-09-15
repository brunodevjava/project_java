package com.dev.project.service;

import com.dev.project.convertions.AbstractConversion;
import com.dev.project.domain.Address;
import com.dev.project.domain.Data;
import com.dev.project.domain.Ncm;
import com.dev.project.dto.DataTO;
import com.dev.project.dto.NcmTO;
import com.dev.project.exception.ResourceNotFoundException;
import com.dev.project.repository.AddressRepository;
import com.dev.project.repository.DataRepository;
import com.dev.project.repository.NcmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Service
public class NcmService {

    @Autowired
    private AbstractConversion conversion;

    @Autowired
    private NcmRepository repository;
    @Autowired
    private AddressRepository addressRepository;

    public NcmTO save(NcmTO dto, HttpServletRequest request) {
        Ncm ncm = (Ncm) conversion.convert(dto, new Ncm());
        NcmTO response = (NcmTO) conversion.convert(repository.save(ncm), new NcmTO());
        return response;
    }

    public void delete(Long id, HttpServletRequest request) {
        repository.deleteById(id);
    }


    public List<NcmTO> getAll() {
        return (List<NcmTO>) conversion.convertList(repository.findAll(), new ArrayList<NcmTO>(), "com.dev.project.dto.NcmTO");
    }

    public NcmTO getById(Long id) {
        return (NcmTO) conversion.convert(repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Ncm not found.")), new NcmTO());
    }

    public NcmTO update(NcmTO dto, HttpServletRequest request) {
        Ncm ncm = repository.findById(dto.getId()).orElseThrow(() -> new ResourceNotFoundException("Ncm not found."));
        ncm = (Ncm) conversion.convert(dto, ncm);

        return (NcmTO) conversion.convert(repository.save(ncm), new NcmTO());
    }


}
