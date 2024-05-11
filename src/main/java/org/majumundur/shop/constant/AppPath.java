package org.majumundur.shop.constant;

public class AppPath {
//    GLOBAL
    public final static String BASE = "/api";
    public final static String ID = "/{id}";

//    AUTH
    public final static String BASE_AUTH = BASE + "/auth";
    public final static String REGISTER_CUSTOMER = "/register";
    public final static String REGISTER_MERCHANT = "/register/merchant";
    public final static String LOGIN = "/login";

//    MERCHANT
    public final static String BASE_MERCHANT = BASE + "/merchant";

//    CUSTOMER
    public final static String BASE_CUSTOMER = BASE + "/customer";
    public final static String CUSTOMER_ORDER = "/order/my-merchant";

//    PRODUCT
    public final static String BASE_PRODUCT = BASE + "/product";

//    ORDER
    public final static String BASE_ORDER = BASE + "/order";

//    REWARD
    public final static String BASE_REWARD = BASE + "/reward";
}
