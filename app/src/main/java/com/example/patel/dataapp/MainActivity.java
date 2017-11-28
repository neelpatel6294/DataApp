package com.example.patel.dataapp;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.example.patel.dataapp.Data.ProductContract;
import com.example.patel.dataapp.Data.ProductHelper;

public class MainActivity extends AppCompatActivity {


    private ProductHelper mProductHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Setup FAB to open EditorActivity
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.floatingActionButton);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddItem.class);
                startActivity(intent);
            }
        });

        mProductHelper = new ProductHelper(this);
        displayDataBaseInfo();
    }

    @Override
    protected void onStart() {
        super.onStart();
        displayDataBaseInfo();
    }

    private void displayDataBaseInfo() {

        //Create and/or open a database to read from it
        SQLiteDatabase db = mProductHelper.getReadableDatabase();

        String[] projection = {
                ProductContract.ProductEntry._ID,
                ProductContract.ProductEntry.COLUMN_PRODUCT_NAME,
                ProductContract.ProductEntry.COLUMN_PRODUCT_PRICE,
                ProductContract.ProductEntry.COLUMN_PRODUCT_Quantity,
                ProductContract.ProductEntry.COLUMN_PRODUCT_MADE_BY
        };

        Cursor cursor = db.query(
                ProductContract.ProductEntry.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null
        );

        TextView textView = (TextView) findViewById(R.id.productItemID);

        try {
            textView.setText("Product contains " + cursor.getCount() + "products.\n\n");
            textView.append(ProductContract.ProductEntry._ID + " - " +
                    ProductContract.ProductEntry.COLUMN_PRODUCT_NAME + " - " +
                    ProductContract.ProductEntry.COLUMN_PRODUCT_PRICE + " - " +
                    ProductContract.ProductEntry.COLUMN_PRODUCT_Quantity + " - " +
                    ProductContract.ProductEntry.COLUMN_PRODUCT_MADE_BY);


            int idColumnIndex = cursor.getColumnIndex(ProductContract.ProductEntry._ID);
            int nameColumnIndex = cursor.getColumnIndex(ProductContract.ProductEntry.COLUMN_PRODUCT_NAME);
            int priceColumnIndex = cursor.getColumnIndex(ProductContract.ProductEntry.COLUMN_PRODUCT_PRICE);
            int quantityColumnIndex = cursor.getColumnIndex(ProductContract.ProductEntry.COLUMN_PRODUCT_Quantity);
            int madeByColumnIndex = cursor.getColumnIndex(ProductContract.ProductEntry.COLUMN_PRODUCT_MADE_BY);

            while (cursor.moveToNext()) {
                int currentID = cursor.getInt(idColumnIndex);
                String currentName = cursor.getString(nameColumnIndex);
                String currentprice = cursor.getString(priceColumnIndex);
                String currentQauntity = cursor.getString(quantityColumnIndex);
                String currentMadeby = cursor.getString(madeByColumnIndex);

                textView.append(("\n" + currentID + " - " +
                        currentName + " - " +
                        currentprice + " - " +
                        currentQauntity + " - " +
                        currentMadeby));
            }

        } finally {
            cursor.close();
        }

    }
}
