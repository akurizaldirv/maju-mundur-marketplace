package org.majumundur.shop.model.entity;

import jakarta.persistence.*;
import lombok.*;
import org.majumundur.shop.util.enums.EStatus;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "m_product")
@Builder(toBuilder = true)
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private EStatus status;

    @OneToMany(mappedBy = "product")
    private List<ProductPrice> productPrices;
}
