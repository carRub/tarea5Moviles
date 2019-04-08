package com.iteso.sesion9;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.iteso.sesion9.beans.Category;
import com.iteso.sesion9.beans.ItemProduct;
import com.iteso.sesion9.beans.Store;
import com.iteso.sesion9.database.CategoryControl;
import com.iteso.sesion9.database.DataBaseHandler;
import com.iteso.sesion9.database.ItemProductControl;
import com.iteso.sesion9.database.StoreControl;

import java.util.ArrayList;

public class ActivityItem extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Spinner drawables, categories, stores;
    EditText titleText;
    Button save;
    String store, category, image;
    DataBaseHandler db = DataBaseHandler.getInstance(this);

    CategoryControl categoryControl = new CategoryControl();
    StoreControl storeControl = new StoreControl();
    ItemProductControl itemProductControl = new ItemProductControl();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);

        //Spinner with the names of the drawables
        drawables = findViewById(R.id.activity_item_images);
        ArrayAdapter<CharSequence> adapterDrawables = ArrayAdapter.createFromResource(this, R.array.drawables_array, android.R.layout.simple_spinner_item);
        adapterDrawables.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        drawables.setAdapter(adapterDrawables);

        drawables.setOnItemSelectedListener(this);


        //Set title from the edit text
        titleText = findViewById(R.id.activity_item_title);
        final String title = titleText.getText().toString().trim();


        //Spinner with the categories
        categories = findViewById(R.id.activity_item_categories);
        ArrayList<Category> categoriesArray = categoryControl.getCategories(db);
        ArrayList<CharSequence> categoriesNames = new ArrayList<>();

        for(int i = 0; i < categoriesArray.size(); i ++)
            categoriesNames.add(categoriesArray.get(i).getName());

        ArrayAdapter<CharSequence> adapterCategories = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, categoriesNames);

        adapterCategories.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categories.setAdapter(adapterCategories);

        categories.setOnItemSelectedListener(this);


        //Spinner with Stores
        stores = (Spinner) findViewById(R.id.activity_item_stores);
        ArrayList<Store> storesArray = storeControl.getStores(db);

        ArrayList<CharSequence> storesNames = new ArrayList<>();

        for(int i = 0; i < storesArray.size(); i ++)
            storesNames.add(storesArray.get(i).getName());

        ArrayAdapter<CharSequence> adapterStores = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, storesNames);
        adapterStores.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        stores.setAdapter(adapterStores);

        stores.setOnItemSelectedListener(this);


        //Save button
        save = findViewById(R.id.activity_item_save);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //save the item product in the DB
                ItemProduct product = new ItemProduct();

                switch (image){
                    case "alienware":
                        product.setImage(R.drawable.alienware);
                    case "mac":
                        product.setImage(R.drawable.alienware);
                    case "micro":
                        product.setImage(R.drawable.micro);
                    case "pillows":
                        product.setImage(R.drawable.pillows);
                    case "refrigerator":
                        product.setImage(R.drawable.refrigerator);
                    case "sheets":
                        product.setImage(R.drawable.sheets);
                    default:
                        product.setImage(R.drawable.mac);

                }

                product.setTitle(title);
                product.setCategory(categoryControl.getCategoryByName(category, db));
                product.setStore(storeControl.getStoreById(storeControl.getStoreIdByName(store, db), db));

                itemProductControl.addItemProduct(product, db);
                finish();
            }
        });
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getId()){
            case R.id.activity_item_images:
                image = (String) parent.getItemAtPosition(position);
                break;
            case R.id.activity_item_categories:
                category = (String) parent.getItemAtPosition(position);
                break;
            case R.id.activity_item_stores:
                store = (String) parent.getItemAtPosition(position);
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

        image = "mac";
        category = "technology";
        store = "BestBuy Ciudadela";

    }
}
