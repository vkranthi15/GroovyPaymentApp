package com.imobile3.groovypayments.ui.orderhistory;

import com.imobile3.groovypayments.concurrent.GroovyExecutors;
import com.imobile3.groovypayments.data.CartRepository;
import com.imobile3.groovypayments.data.Result;

import java.util.ArrayList;
import java.util.List;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

/**
 * The ViewModel serves as an async bridge between the View (Activity, Fragment)
 * and our backing data repository (Database).
 */
public class OrderHistoryViewModel extends ViewModel {

    private int mCartClicks;
    private final CartRepository mRepository;

    OrderHistoryViewModel(CartRepository repository) {
        mCartClicks = 0;
        mRepository = repository;
    }

    public void addCartClick() {
        mCartClicks++;
    }

    public int getCartClicks() {
        return mCartClicks;
    }

    public LiveData<List<CartAndProducts>> getProductsAddedToCart() {
        // Caller should observe this object for changes. When the data has finished
        // async loading, the observer can react accordingly.
        final MutableLiveData<List<CartAndProducts>> orderHistoryLiveData =
                new MutableLiveData<>(new ArrayList<>());

        GroovyExecutors.getInstance().getDiskIo().execute(() -> {
            Result<List<CartAndProducts>> result = mRepository.getProductsAddedToCart();
            if (result instanceof Result.Success) {
                List<CartAndProducts> resultSet = ((Result.Success<List<CartAndProducts>>)result).getData();
                orderHistoryLiveData.postValue(resultSet);
            } else {
                orderHistoryLiveData.postValue(new ArrayList<>());
            }
        });

        return orderHistoryLiveData;
    }
}
