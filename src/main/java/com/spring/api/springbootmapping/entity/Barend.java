package com.spring.api.springbootmapping.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@Table(name="barend_tbl")
public class Barend {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull(message = "ProdName is cannot Empty")
    @NotEmpty(message = "ProdName is cannot Empty")
    @NotBlank(message = "ProdName is cannot Blank")
    private String prodName;

    @NotBlank(message = "Quality is cannot Blank")
    @NotEmpty(message = "Quality is cannot Empty")
    @Column(name = "quality")
    private String quality;

    @ManyToOne
    @JoinColumn(name="product_id")
    private Product product;
}
