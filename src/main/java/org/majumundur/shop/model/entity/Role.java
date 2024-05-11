package org.majumundur.shop.model.entity;

import jakarta.persistence.*;
import lombok.*;
import org.majumundur.shop.util.enums.ERole;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "m_role")
@Builder(toBuilder = true)
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(value = EnumType.STRING)
    private ERole role;
}
