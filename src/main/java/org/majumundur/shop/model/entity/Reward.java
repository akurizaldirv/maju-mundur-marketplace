package org.majumundur.shop.model.entity;

import jakarta.persistence.*;
import lombok.*;
import org.majumundur.shop.util.enums.EReward;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "m_reward")
@Builder(toBuilder = true)
public class Reward {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    private EReward reward;

    @OneToMany(mappedBy = "reward")
    private List<CustomerReward> customerRewards;
}
