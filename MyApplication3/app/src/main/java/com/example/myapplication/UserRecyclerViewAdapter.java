package com.example.myapplication;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.db.UserDAO;

import java.util.ArrayList;
import java.util.List;

public class UserRecyclerViewAdapter extends RecyclerView.Adapter<UserRecyclerViewAdapter.ViewHolder> {
    private static final String TAG = "UserRecyclerViewAdapter";


    private ArrayList<String> allUserInfo = new ArrayList<>();
    private Context context;
    UserDAO userDAO;

    public UserRecyclerViewAdapter(Context context, ArrayList<String> allUserInfo) {
        this.allUserInfo = allUserInfo;
        this.context = context;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //where we inflate the layout and give a look to our rows
        Log.d(TAG, "onCreateViewHolder: ");

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_user_listitem, parent, false);
        ViewHolder holder = new ViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //assigning values to the views we created in the  layout file based on the position of the recycler view
        Log.d(TAG, "onBindViewHolder: ");

        holder.userInfo.setText(allUserInfo.get(position));

    }


    @Override
    public int getItemCount() {
        //return the number of items total in the userarray list
        return allUserInfo.size();
    }

        public static class ViewHolder extends RecyclerView.ViewHolder{
        //grabbing the views from item layout file
        //create the variables

        TextView userInfo;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            //wire it up
            userInfo = itemView.findViewById(R.id.user_listitem_tv_userinfo);
        }
    }
//    public void removeItem(int position){
//        allUserInfo.remove(position);
//        notifyDataSetChanged();
//    }
}
