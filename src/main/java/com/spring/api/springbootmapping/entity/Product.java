package com.spring.api.springbootmapping.entity;

import com.spring.api.springbootmapping.request.ProductRequest;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@ToString
@Table(name="product_tbl")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull(message = "ProdName is cannot Empty")
    @NotEmpty(message = "ProdName is cannot Empty")
    @NotBlank(message = "ProdName is cannot Blank")
    private String prodName;

    @NotNull(message = "Quality is cannot Empty")
    @NotEmpty(message = "Quality is cannot Empty")
    @NotBlank(message = "Quality is cannot Blank")
    private String quality;


    @OneToMany(mappedBy="product")
    private List<Barend> barend;

    public  Product(@Valid ProductRequest productRequest) {
        this.prodName = productRequest.getProdName();
        this.quality=productRequest.getQuality();
    }

}
