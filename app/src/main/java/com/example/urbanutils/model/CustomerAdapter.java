package com.example.urbanutils.model;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.urbanutils.R;

import java.util.ArrayList;

public class CustomerAdapter extends RecyclerView.Adapter<CustomerAdapter.CustomerViewHolder> {

    private ArrayList<ChatModel> arrayList;

    public CustomerAdapter(ArrayList<ChatModel> arrayList){
        this.arrayList=arrayList;
    }

    @NonNull
    @Override
    public CustomerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.customer_list_view,parent,false);

        return new CustomerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomerViewHolder holder, int position) {
        ChatModel obj=arrayList.get(position);
        holder.messageTextview.setText(obj.getMessage());
        holder.phoneNumber.setText(""+obj.getPhone());


    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class CustomerViewHolder extends RecyclerView.ViewHolder{

        private TextView messageTextview,phoneNumber;


        public CustomerViewHolder(@NonNull View itemView) {
            super(itemView);
            messageTextview=itemView.findViewById(R.id.message_id);
            phoneNumber=itemView.findViewById(R.id.customer_phone_id);
        }
    }
}
