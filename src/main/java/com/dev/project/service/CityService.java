package com.dev.project.service;

import com.dev.project.convertions.AbstractConversion;
import com.dev.project.domain.Cfop;
import com.dev.project.domain.City;
import com.dev.project.dto.CfopTO;
import com.dev.project.dto.CityTO;
import com.dev.project.exception.ResourceNotFoundException;
import com.dev.project.repository.CfopRepository;
import com.dev.project.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Service
public class CityService {

    @Autowired
    private AbstractConversion conversion;

    @Autowired
    private CityRepository repository;

    public CityTO save(CityTO dto, HttpServletRequest request) {
        City city = (City) conversion.convert(dto, new CityTO());
        CityTO response = (CityTO) conversion.convert(repository.save(city), new CityTO());
        return response;
    }

    public void delete(Long id, HttpServletRequest request) {
        repository.deleteById(id);
    }


    public List<CityTO> getAll() {
        return (List<CityTO>) conversion.convertList(repository.findAll(), new ArrayList<CityTO>(), "com.dev.project.dto.CityTO");
    }

    public CityTO getById(Long id) {
        return (CityTO) conversion.convert(repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("City not found.")), new CityTO());
    }

    public CityTO update(CityTO dto, HttpServletRequest request) {
        City city = repository.findById(dto.getId()).orElseThrow(() -> new ResourceNotFoundException("City not found."));
        city = (City) conversion.convert(dto, city);

        return (CityTO) conversion.convert(repository.save(city), new CityTO());
    }


}
