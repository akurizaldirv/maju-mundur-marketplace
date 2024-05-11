package org.majumundur.shop.model.entity;

import jakarta.persistence.*;
import lombok.*;
import org.majumundur.shop.util.enums.EStatus;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "m_product_price")
@Builder(toBuilder = true)
public class ProductPrice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private Long price;

    @Column(nullable = false)
    private Integer stock;

    @Enumerated(EnumType.STRING)
    private EStatus status;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "merchant_id", nullable = false)
    private Merchant merchant;

    @OneToMany(mappedBy = "productPrice")
    private List<OrderDetail> orderDetails;
}
