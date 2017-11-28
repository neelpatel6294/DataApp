package com.example.patel.dataapp.Data;

import android.provider.BaseColumns;

/**
 * Created by PATEL on 11/28/2017.
 */

public class ProductContract {


    public ProductContract() {
    }

    public static final class ProductEntry implements BaseColumns {

        //Name of Table
        public final static String TABLE_NAME = "products";

        //ID of Products
        public final static String _ID = BaseColumns._ID;

        //NAME of products
        public final static String COLUMN_PRODUCT_NAME = "name";

        //Price of Product
        public final static String COLUMN_PRODUCT_PRICE = "price";

        //Quantity of Product
        public final static String COLUMN_PRODUCT_Quantity = "quantity";

        //Product Made by
        public final static String COLUMN_PRODUCT_MADE_BY = "madeBY";
    }
}
