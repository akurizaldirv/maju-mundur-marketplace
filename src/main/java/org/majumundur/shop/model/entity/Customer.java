package org.majumundur.shop.model.entity;


import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "m_customer")
@Builder(toBuilder = true)
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true, name = "identity_number")
    private String identityNumber;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, name = "phone_number", unique = true)
    private String phone;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false, name = "points_obtained")
    private Integer pointsObtained;

    @OneToMany(mappedBy = "customer")
    private List<Order> orders;

    @OneToMany(mappedBy = "customer")
    private List<CustomerReward> customerRewards;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_credential_id", nullable = false)
    private UserCredential userCredential;
}
