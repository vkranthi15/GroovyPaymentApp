package com.imobile3.groovypayments.ui.orderhistory;

import java.util.Date;
import java.util.List;

public class CartAndProducts {

    private final long cartId;
    private final long grandTotal;
    private final Date dateCreated;
    private final List<String> products;

    public CartAndProducts(long cartId, long grandTotal, Date dateCreated, List<String> products) {
        this.cartId = cartId;
        this.grandTotal = grandTotal;
        this.dateCreated = dateCreated;
        this.products = products;
    }

    public long getCartId() {
        return cartId;
    }

    public long getGrandTotal() {
        return grandTotal;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public List<String> getProducts() {
        return products;
    }
}
