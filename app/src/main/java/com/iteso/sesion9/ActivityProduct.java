package com.iteso.sesion9;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.iteso.sesion9.beans.ItemProduct;
import com.iteso.sesion9.tools.Constants;

public class ActivityProduct extends AppCompatActivity {

    EditText title, location, store, phone;
    ImageView image;
    Button save, cancel;
    ItemProduct itemProduct;
    int fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        image = findViewById(R.id.activity_product_image);
        title = findViewById(R.id.activity_product_title);
        location = findViewById(R.id.activity_product_location);
        store = findViewById(R.id.activity_product_store);
        phone = findViewById(R.id.activity_product_phone);
        save = findViewById(R.id.activity_product_save);
        cancel = findViewById(R.id.activity_product_cancel);

        fragment = getIntent().getExtras().getInt(Constants.EXTRA_FRAGMENT, -1);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemProduct.setTitle(title.getText().toString());
                //itemProduct.setLocation(location.getText().toString());
                //itemProduct.setStore(store.getText().toString());
                //itemProduct.setPhone(phone.getText().toString());
                Intent intent = new Intent();
                intent.putExtra(Constants.EXTRA_PRODUCT, itemProduct);
                intent.putExtra(Constants.EXTRA_FRAGMENT, fragment);

                setResult(RESULT_OK, intent);
                finish();
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(RESULT_CANCELED);
                finish();
            }
        });

        if (getIntent().getExtras() != null) {
            itemProduct = getIntent().getParcelableExtra(Constants.EXTRA_PRODUCT);
            if (itemProduct != null) {
                title.setText(itemProduct.getTitle());
                //location.setText(itemProduct.getLocation());
                //store.setText(itemProduct.getStore());
                //phone.setText(itemProduct.getPhone());
                switch (itemProduct.getImage()) {
                    case Constants.TYPE_MAC:
                        image.setImageResource(R.drawable.mac);
                        break;
                    case Constants.TYPE_ALIENWARE:
                        image.setImageResource(R.drawable.alienware);
                        break;
                    case Constants.TYPE_SHEETS:
                        image.setImageResource(R.drawable.sheets);
                        break;
                    case Constants.TYPE_PILLOW:
                        image.setImageResource(R.drawable.pillows);
                        break;
                    case Constants.TYPE_REFRIGERATOR:
                        image.setImageResource(R.drawable.refrigerator);
                        break;
                    case Constants.TYPE_MICRO:
                        image.setImageResource(R.drawable.micro);
                        break;
                }
                Bitmap bitmap = ((BitmapDrawable) image.getDrawable()).getBitmap();
                image.setImageBitmap(bitmap);
            }
        }

    }
}
