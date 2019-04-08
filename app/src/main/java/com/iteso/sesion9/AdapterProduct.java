package com.iteso.sesion9;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.iteso.sesion9.beans.ItemProduct;
import com.iteso.sesion9.tools.Constants;

import java.util.ArrayList;

public class AdapterProduct extends RecyclerView.Adapter<AdapterProduct.ViewHolder> {

    private static final String TAG = "AdapterProduct";

    private ArrayList<ItemProduct> mDataSet;
    private Context context;
    private int fragment;

    AdapterProduct(int fragment, Context context, ArrayList<ItemProduct> products){
        this.fragment = fragment;
        this.mDataSet = products;
        this.context = context;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public AdapterProduct.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                        int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_product, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        if(mDataSet.get(position) != null) {
            holder.mProductTitle.setText(mDataSet.get(position).getTitle());
            holder.mProductStore.setText(mDataSet.get(position).getStore().getName());
            holder.mProductLocation.setText(mDataSet.get(position).getStore().getCity().getName());
            holder.mProductPhone.setText(mDataSet.get(position).getStore().getPhone());

            switch (mDataSet.get(holder.getAdapterPosition()).getImage()) {
                case Constants.TYPE_MAC:
                    holder.mProductImage.setImageResource(R.drawable.mac);
                    break;
                case Constants.TYPE_ALIENWARE:
                    holder.mProductImage.setImageResource(R.drawable.alienware);
                    break;
                case Constants.TYPE_SHEETS:
                    holder.mProductImage.setImageResource(R.drawable.sheets);
                    break;
                case Constants.TYPE_PILLOW:
                    holder.mProductImage.setImageResource(R.drawable.pillows);
                    break;
                case Constants.TYPE_REFRIGERATOR:
                    holder.mProductImage.setImageResource(R.drawable.refrigerator);
                    break;
                case Constants.TYPE_MICRO:
                    holder.mProductImage.setImageResource(R.drawable.micro);
                    break;
            }
        }

        holder.mEventLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(context, mDataSet.get(position).toString(),
                //        Toast.LENGTH_LONG).show();

                Intent intent = new Intent(context, ActivityProduct.class);
                intent.putExtra(Constants.EXTRA_PRODUCT, mDataSet.get(holder.getAdapterPosition()));
                intent.putExtra(Constants.EXTRA_FRAGMENT, fragment);
                ((MainActivity) context).startActivityForResult(intent, Constants.ACTIVITY_DETAIL);
            }
        });

        holder.mProductPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL,
                        Uri.parse("tel:" + mDataSet.get(holder.getAdapterPosition()).getStore().getPhone()));
                context.startActivity(intent);

            }
        });

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataSet.size();
    }


    static class ViewHolder extends RecyclerView.ViewHolder {
        Button mDetail;
        TextView mProductTitle;
        TextView mProductStore;
        TextView mProductLocation;
        TextView mProductPhone;
        ImageView mProductImage;
        ImageView mProductThumbnail;
        RelativeLayout mEventLayout;

        ViewHolder(View v) {
            super(v);
            mEventLayout = v.findViewById(R.id.item_product_layout);
            mDetail = v.findViewById(R.id.item_product_detail);
            mProductTitle = v.findViewById(R.id.item_product_title);
            mProductStore = v.findViewById(R.id.item_product_store);
            mProductLocation = v.findViewById(R.id.item_product_location);
            mProductPhone = v.findViewById(R.id.item_product_phone);
            mProductImage = v.findViewById(R.id.item_product_image);
            mProductThumbnail = v.findViewById(R.id.item_product_thumbnail);
        }
    }

}