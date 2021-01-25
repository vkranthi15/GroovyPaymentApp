package com.imobile3.groovypayments.data;

import com.imobile3.groovypayments.data.dao.CartProductDao;
import com.imobile3.groovypayments.data.model.Cart;
import com.imobile3.groovypayments.ui.orderhistory.CartAndProducts;

import androidx.annotation.WorkerThread;

import java.util.ArrayList;
import java.util.List;

public class CartDataSource {

    public CartDataSource() {
    }

    @WorkerThread
    public Result<List<Cart>> loadCarts() {
        List<Cart> results =
                DatabaseHelper.getInstance().getDatabase().getCartDao().getCarts();
        return new Result.Success<>(results);
    }

    @WorkerThread
    public Result<List<CartAndProducts>> loadProductsFromCart() {
        List<CartAndProducts> cartAndProductsList = new ArrayList<>();
        List<Cart> results =
                DatabaseHelper.getInstance().getDatabase().getCartDao().getCarts();

        CartProductDao cartProductDao = DatabaseHelper.getInstance().getDatabase().getCartProductDao();

        if(results != null && results.size() > 0) {
            for (int i = 0; i < results.size(); i++) {
                Cart cart = results.get(i);
                cartAndProductsList.add(new CartAndProducts(cart.getId(),
                        cart.getGrandTotal(),
                        cart.getDateCreated(),
                        cartProductDao.getCartProductsWithCartId(cart.getId())));
            }
        }
        return new Result.Success<>(cartAndProductsList);
    }
}
