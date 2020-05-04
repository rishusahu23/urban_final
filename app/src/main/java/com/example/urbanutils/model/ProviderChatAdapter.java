package com.example.urbanutils.model;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.urbanutils.R;

import java.util.ArrayList;

public class ProviderChatAdapter extends RecyclerView.Adapter<ProviderChatAdapter.ProviderChatViewholder> {

    private ArrayList<ChatModel> arrayList;

    public ProviderChatAdapter( ArrayList<ChatModel> arrayList){
        this.arrayList=arrayList;
    }
    @NonNull
    @Override
    public ProviderChatViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.provider_chat_list_view,parent,false);

        return new ProviderChatViewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProviderChatViewholder holder, int position) {
        ChatModel obj=arrayList.get(position);
        holder.phoneTextview.setText(obj.getPhone());
        holder.messagetextView.setText(obj.getMessage());
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ProviderChatViewholder extends RecyclerView.ViewHolder{
        TextView messagetextView,phoneTextview;
        public ProviderChatViewholder(@NonNull View itemView) {
            super(itemView);
            messagetextView=itemView.findViewById(R.id.message_id);
            phoneTextview=itemView.findViewById(R.id.phone_id);
        }
    }
}
