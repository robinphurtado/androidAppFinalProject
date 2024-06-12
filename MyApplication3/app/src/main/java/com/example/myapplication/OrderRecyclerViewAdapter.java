package com.example.myapplication;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.db.OrderDAO;
import com.example.myapplication.db.ProductDAO;

import java.util.ArrayList;

public class OrderRecyclerViewAdapter extends RecyclerView.Adapter<OrderRecyclerViewAdapter.ViewHolder> {
    private static final String TAG = "OrderRecyclerViewAdapte";


    private ArrayList<String> allOrderInfo = new ArrayList<>();
    private Context context;
    OrderDAO orderDAO;

    public OrderRecyclerViewAdapter(Context context, ArrayList<String> allOrderInfo) {
        this.allOrderInfo = allOrderInfo;
        this.context = context;
    }


    @NonNull
    @Override
    public OrderRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //where we inflate the layout and give a look to our rows
        Log.d(TAG, "onCreateViewHolder: ");



        //LayoutInflater inflater = LayoutInflater.from(context);
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_order_listitem, parent, false);
        OrderRecyclerViewAdapter.ViewHolder holder = new OrderRecyclerViewAdapter.ViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull OrderRecyclerViewAdapter.ViewHolder holder, int position) {
        //assigning values to the views we created in the  layout file based on the position of the recycler view
        Log.d(TAG, "onBindViewHolder: ");

        holder.orderInfo.setText(allOrderInfo.get(position));

    }

    @Override
    public int getItemCount() {
        //return the number of items total in the product array list
        return allOrderInfo.size();
    }



    public static class ViewHolder extends RecyclerView.ViewHolder{
        //grabbing the views from item layout file
        //create the variables

        TextView orderInfo;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            //wire it up
            orderInfo = itemView.findViewById(R.id.order_listitem_tv_userinfo);
        }
    }
}
