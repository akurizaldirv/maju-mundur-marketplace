package org.majumundur.shop.util.enums;

public enum EReward {
    REWARD_A(20), REWARD_B(40);
    private final Integer price;

    EReward(Integer price) {
        this.price = price;
    }

    public Integer getPrice() {
        return price;
    }
}
