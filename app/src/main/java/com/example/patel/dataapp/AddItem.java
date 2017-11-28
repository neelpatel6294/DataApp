package com.example.patel.dataapp;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.patel.dataapp.Data.ProductContract;
import com.example.patel.dataapp.Data.ProductHelper;

public class AddItem extends AppCompatActivity {

    private EditText mName;

    private EditText mPrice;

    private Button mAdd;

    private Button mDelete;

    private EditText mMadeBy;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);


        mName = (EditText) findViewById(R.id.productNameId);
        mPrice = (EditText) findViewById(R.id.priceId);
        mAdd = (Button)findViewById(R.id.addQuantityButton);
        mDelete = (Button)findViewById(R.id.deleteQuantityButton);
        mMadeBy = (EditText)findViewById(R.id.madeBy);

    }

    private void insertProduct(){
        String nameString = mName.getText().toString().trim();
        int priceString = Integer.parseInt(mPrice.getText().toString().trim());
        String madeByString = mMadeBy.getText().toString().trim();

        ProductHelper mHelper = new ProductHelper(this);

        SQLiteDatabase db = mHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(ProductContract.ProductEntry.COLUMN_PRODUCT_NAME,nameString);
        values.put(ProductContract.ProductEntry.COLUMN_PRODUCT_PRICE,priceString);
        values.put(ProductContract.ProductEntry.COLUMN_PRODUCT_MADE_BY,madeByString);

        long newRowID = db.insert(ProductContract.ProductEntry.TABLE_NAME,null,values);


        // Show a toast message depending on whether or not the insertion was successful
        if (newRowID == -1) {
            // If the row ID is -1, then there was an error with insertion.
            Toast.makeText(this, "Error with saving product", Toast.LENGTH_SHORT).show();
        } else {
            // Otherwise, the insertion was successful and we can display a toast with the row ID.
            Toast.makeText(this, "product saved with row id: " + newRowID, Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu options from the res/menu/menu_editor.xml file.
        // This adds menu items to the app bar.
        getMenuInflater().inflate(R.menu.menu_add_item, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // User clicked on a menu option in the app bar overflow menu
        switch (item.getItemId()) {
            // Respond to a click on the "Save" menu option
            case R.id.save:
                // Do nothing for now
                insertProduct();
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
