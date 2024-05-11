package org.majumundur.shop.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "m_merchant")
@Builder(toBuilder = true)
public class Merchant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, name = "phone_number", unique = true)
    private String phone;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String address;

    @OneToMany(mappedBy = "merchant")
    private List<ProductPrice> productPrices;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_credential_id", nullable = false)
    private UserCredential userCredential;
}
