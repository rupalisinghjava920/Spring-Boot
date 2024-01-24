package com.spring.api.springbootmapping.controller;

import com.spring.api.springbootmapping.entity.Barend;
import com.spring.api.springbootmapping.repository.BarendRepository;
import com.spring.api.springbootmapping.response.BarendResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class BarendController {

    @Autowired
    private BarendRepository barendRepository;

    @GetMapping("/barends")
    public List<BarendResponse> getBarend() {
        List<Barend> bars = barendRepository.findAll();
        List<BarendResponse> list = new ArrayList<>();
        bars.forEach(barend -> {
            BarendResponse barendResponse = new BarendResponse();
            barendResponse.setId(barend.getId());
            barendResponse.setBarendName(barend.getProdName());
            barendResponse.setProductName(barend.getProdName());
            list.add(barendResponse);
        });
        return list;
    }
}