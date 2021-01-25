package com.imobile3.groovypayments.ui.orderhistory;


import java.util.List;

import androidx.annotation.Nullable;

public class OrderHistoryResult {

    @Nullable
    private Integer error;
    @Nullable
    private List<CartAndProducts> success;

    OrderHistoryResult(@Nullable Integer error) {
        this.error = error;
    }

    OrderHistoryResult(@Nullable List<CartAndProducts> success) {
        this.success = success;
    }

    @Nullable
    List<CartAndProducts> getSuccess() {
        return success;
    }

    @Nullable
    Integer getError() {
        return error;
    }
}
