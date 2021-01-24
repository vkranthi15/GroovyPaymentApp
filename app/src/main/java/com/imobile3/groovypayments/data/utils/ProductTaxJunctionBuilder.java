package com.imobile3.groovypayments.data.utils;

import com.imobile3.groovypayments.data.entities.ProductTaxJunctionEntity;

import androidx.annotation.NonNull;

public final class ProductTaxJunctionBuilder {

    private ProductTaxJunctionBuilder() {
    }

    @NonNull
    public static ProductTaxJunctionEntity build(
            long productId,
            long taxId) {
        ProductTaxJunctionEntity result = new ProductTaxJunctionEntity();
        result.setProductId(productId);
        result.setTaxId(taxId);
        return result;
    }
}

