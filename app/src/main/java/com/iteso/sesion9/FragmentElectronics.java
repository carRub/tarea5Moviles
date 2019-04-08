package com.iteso.sesion9;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.iteso.sesion9.beans.ItemProduct;
import com.iteso.sesion9.database.DataBaseHandler;
import com.iteso.sesion9.database.ItemProductControl;
import com.iteso.sesion9.tools.Constants;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentElectronics extends Fragment {

    RecyclerView recyclerView;
    DataBaseHandler db = DataBaseHandler.getInstance(getActivity());
    ItemProductControl itemProductControl = new ItemProductControl();

    ArrayList<ItemProduct> products;

    public FragmentElectronics() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_custom, container, false);
        recyclerView = rootView.findViewById(R.id.fragment_recycler);
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        recyclerView.setHasFixedSize(true);
        // Use a linear layout manager
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);

        products = itemProductControl.getItemProductsByCategory(Constants.FRAGMENT_ELECTRONICS, db);
        //products.add(new ItemProduct(6, "Refrigerador", "BestBuy", "Zapopan", "3312345678", "Lorem Ipsum ....", Constants.TYPE_REFRIGERATOR));
        //products.add(new ItemProduct(7, "Micro", "Palacio de Hierro", "Zapopan", "3312345678", "Lorem Ipsum ....", Constants.TYPE_MICRO));

        AdapterProduct adapterProduct = new AdapterProduct(Constants.FRAGMENT_ELECTRONICS, getActivity(), products);
        recyclerView.setAdapter(adapterProduct);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
}
