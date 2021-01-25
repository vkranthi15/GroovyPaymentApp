package com.imobile3.groovypayments.data;

import com.imobile3.groovypayments.ui.orderhistory.CartAndProducts;

import java.util.List;

/**
 * A repository holds a reference to different kinds of data sources
 * (web services, local database or cached data).
 */
public class CartRepository {

    private static volatile CartRepository sInstance;

    private CartDataSource mDataSource;

    private CartRepository(CartDataSource dataSource) {
        mDataSource = dataSource;
    }

    public static synchronized CartRepository getInstance(CartDataSource dataSource) {
        if (sInstance == null) {
            sInstance = new CartRepository(dataSource);
        }
        return sInstance;
    }

    public CartDataSource getDataSource() {
        return mDataSource;
    }

    public Result<List<CartAndProducts>> getProductsAddedToCart() {
        return getDataSource().loadProductsFromCart();
    }
}
