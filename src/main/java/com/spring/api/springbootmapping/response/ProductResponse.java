package com.spring.api.springbootmapping.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ProductResponse {

    private Long id;
    private String productName;
    private List<String> barend;
}
