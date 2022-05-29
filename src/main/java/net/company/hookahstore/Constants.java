package net.company.hookahstore;

public class Constants {
    public final static int ITEMS_PER_PAGE = 8;
    public final static String CATEGORY_LIST = "CATEGORY_LIST";
    public final static String PRODUCER_LIST = "PRODUCER_LIST";
    public final static String PRODUCER_BY_CATEGORY_MAP = "PRODUCER_BY_CATEGORY_MAP";
    public final static int MAX_PRODUCTS_PER_SHOPPING_CART = 20;
    public final static int MAX_PRODUCT_COUNT = 10;
    public final static int MAX_ORDERS_PER_PAGE = 7;
    public final static String CURRENT_SHOPPING_CART = "CURRENT_SHOPPING_CART";
    public final static String CURRENT_ACCOUNT = "CURRENT_ACCOUNT";
    public final static String SUCCESS_REDIRECT_AFTER_SIGNIN = "SUCCESS_REDIRECT_AFTER_SIGNIN";
    public final static String USER="USER";
    public final static String ADMIN="ADMIN";

    public enum Cookie {
        //1 year ttl
        SHOPPING_CART("HSSCS", 60 * 60 * 24 * 365);
        private final String name;
        private final int ttl;

        private Cookie(String name, int ttl) {
            this.name = name;
            this.ttl = ttl;
        }

        public String getName() {
            return name;
        }

        public int getTtl() {
            return ttl;
        }
    }
}
