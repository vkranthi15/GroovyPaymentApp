package com.imobile3.groovypayments.data;

import android.content.Context;
import android.os.AsyncTask;

import com.imobile3.groovypayments.MainApplication;
import com.imobile3.groovypayments.data.entities.ProductEntity;
import com.imobile3.groovypayments.data.entities.ProductTaxJunctionEntity;
import com.imobile3.groovypayments.data.entities.TaxEntity;
import com.imobile3.groovypayments.data.enums.GroovyColor;
import com.imobile3.groovypayments.data.enums.GroovyIcon;
import com.imobile3.groovypayments.data.utils.ProductBuilder;
import com.imobile3.groovypayments.data.utils.ProductTaxJunctionBuilder;
import com.imobile3.groovypayments.data.utils.TaxBuilder;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

public final class GroovyDemoManager {

    private static final String TAG = GroovyDemoManager.class.getSimpleName();

    //region Singleton Implementation

    private static GroovyDemoManager sInstance;

    private GroovyDemoManager() {
    }

    @NonNull
    public static synchronized GroovyDemoManager getInstance() {
        if (sInstance == null) {
            sInstance = new GroovyDemoManager();
        }
        return sInstance;
    }

    //endregion

    public interface ResetDatabaseCallback {

        void onDatabaseReset();
    }

    /**
     * Delete the current database instance (potentially dangerous operation!)
     * and populate a new instance with fresh demo data.
     */
    public void resetDatabase(
            @NonNull final ResetDatabaseCallback callback) {
        new ResetDatabaseTask(MainApplication.getInstance(), callback)
                .executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    private class ResetDatabaseTask extends AsyncTask<Void, Void, Void> {

        @NonNull
        private final Context mContext;
        @NonNull
        private final ResetDatabaseCallback mCallback;

        private ResetDatabaseTask(
                @NonNull final Context context,
                @NonNull final ResetDatabaseCallback callback) {
            mContext = context;
            mCallback = callback;
        }

        @Override
        protected Void doInBackground(Void... params) {
            // Blow away any existing database instance.
            DatabaseHelper.getInstance().eraseDatabase(mContext);

            // Initialize a new database instance.
            DatabaseHelper.getInstance().init(mContext);

            // Insert entities into database instance.
            GroovyDatabase database = DatabaseHelper.getInstance().getDatabase();
            database.getProductDao()
                    .insertProducts(getProductEntities());
            database.getTaxDao().insertTaxes(getTaxEntity());
            database.getProductTaxJunctionDao().insertProductTaxJunctions(getProductTaxJunctionEntities());

            // All done!
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            mCallback.onDatabaseReset();
        }
    }

    private List<ProductEntity> getProductEntities() {
        List<ProductEntity> productEntities = new ArrayList<>();

        // Adding list of product as per given mock.
        productEntities.add(ProductBuilder.build(101L,
                "Tasty Chicken Sandwich",
                "Chicken, lettuce, tomato and mayo",
                750L, 200L,
                GroovyIcon.Sandwich, GroovyColor.Yellow));
        productEntities.add(ProductBuilder.build(102L,
                "10-Pack of AA Batteries",
                "Medium-quality batteries",
                899L, 350L,
                GroovyIcon.BatteryPack, GroovyColor.Gray));
        productEntities.add(ProductBuilder.build(103L,
                "Metal Folding Chair",
                "Weighs approximately 12lbs",
                2250L, 1200L,
                GroovyIcon.WoodenChair, GroovyColor.Blue));
        productEntities.add(ProductBuilder.build(104L,
                "Coffee Mug w/ Custom Design",
                "Requires at least 48 hours to fulfill custom order",
                1275L, 650L,
                GroovyIcon.CoffeeMug, GroovyColor.Red));
        productEntities.add(ProductBuilder.build(105L,
                "Google I/O Novelty T-Shirt",
                "Depicts the little green android dude",
                1750L, 800L,
                GroovyIcon.CoffeeMug, GroovyColor.Red));
        productEntities.add(ProductBuilder.build(106L,
                "Super Nintendo Entertainment System",
                "The classic SNES console w/ Super Mario World",
                15000L, 8000L,
                GroovyIcon.RetroController, GroovyColor.Purple));
        productEntities.add(ProductBuilder.build(107L,
                "25-Pack of Snickers Candy Bars",
                "",
                1500L, 700L,
                GroovyIcon.WrappedSweet, GroovyColor.Orange));

        return productEntities;
    }

    private TaxEntity getTaxEntity() {
        // Adding Tax record
        return TaxBuilder.build(1,
                "Sales Tax",
                "0.04");
    }

    private List<ProductTaxJunctionEntity> getProductTaxJunctionEntities() {
        // Adding list of product tax junction records
        List<ProductTaxJunctionEntity> productTaxJunctionEntities = new ArrayList<>();
        productTaxJunctionEntities.add(ProductTaxJunctionBuilder.build(101L, 1L));
        productTaxJunctionEntities.add(ProductTaxJunctionBuilder.build(102L, 1L));
        productTaxJunctionEntities.add(ProductTaxJunctionBuilder.build(103L, 1L));
        productTaxJunctionEntities.add(ProductTaxJunctionBuilder.build(104L, 1L));
        productTaxJunctionEntities.add(ProductTaxJunctionBuilder.build(105L, 1L));
        productTaxJunctionEntities.add(ProductTaxJunctionBuilder.build(106L, 1L));
        productTaxJunctionEntities.add(ProductTaxJunctionBuilder.build(107L, 1L));

        return productTaxJunctionEntities;
    }
}
