package com.dev.project.service;

import com.dev.project.convertions.AbstractConversion;
import com.dev.project.domain.Address;
import com.dev.project.domain.Cfop;
import com.dev.project.domain.Data;
import com.dev.project.dto.CfopTO;
import com.dev.project.dto.DataTO;
import com.dev.project.exception.ResourceNotFoundException;
import com.dev.project.repository.AddressRepository;
import com.dev.project.repository.CfopRepository;
import com.dev.project.repository.DataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Service
public class CfopService {

    @Autowired
    private AbstractConversion conversion;

    @Autowired
    private CfopRepository repository;

    public CfopTO save(CfopTO dto, HttpServletRequest request) {
        Cfop cfop = (Cfop) conversion.convert(dto, new Cfop());
        CfopTO response = (CfopTO) conversion.convert(repository.save(cfop), new CfopTO());
        return response;
    }

    public void delete(Long id, HttpServletRequest request) {
        repository.deleteById(id);
    }


    public List<CfopTO> getAll() {
        return (List<CfopTO>) conversion.convertList(repository.findAll(), new ArrayList<CfopTO>(), "com.dev.project.dto.CfopTO");
    }

    public CfopTO getById(Long id) {
        return (CfopTO) conversion.convert(repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("CFOP not found.")), new CfopTO());
    }

    public CfopTO update(CfopTO dto, HttpServletRequest request) {
        Cfop cfop = repository.findById(dto.getId()).orElseThrow(() -> new ResourceNotFoundException("CFOP not found."));
        cfop = (Cfop) conversion.convert(dto, cfop);

        return (CfopTO) conversion.convert(repository.save(cfop), new CfopTO());
    }


}
