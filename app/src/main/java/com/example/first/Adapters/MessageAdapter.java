package com.example.first.Adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.first.Model.Chat;
import com.example.first.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.List;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.ViewHolder> {

    private static final String TAG = "MessageAdapter";

    private static final int MSG_TYPE_LEFT = 0;
    private static final int MSG_TYPE_RIGHT = 1;
    private Context mContext;
    private List<Chat> mChat;
    private String imgUrl;
    FirebaseUser fuser;

    public MessageAdapter(Context mContext, List<Chat> mChat, String imgUrl) {
        this.mContext = mContext;
        this.mChat = mChat;
        this.imgUrl = imgUrl;
    }

    @NonNull
    @Override
    public MessageAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        if(viewType == MSG_TYPE_RIGHT) {
            view = LayoutInflater.from(mContext).inflate(R.layout.chat_item_right, parent, false);
        } else {
            view = LayoutInflater.from(mContext).inflate(R.layout.chat_item_left, parent, false);
        }
        return new MessageAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MessageAdapter.ViewHolder holder, int position) {
        Chat chat = mChat.get(position);
        holder.message.setText(chat.getMessage());
//        if(imgUrl.equals("default")) {
//            holder.profile_image.setImageResource(R.mipmap.ic_launcher_round);
//        } else {
//            Glide.with(mContext).load(imgUrl).into(holder.profile_image);
//        }

        if(position == mChat.size() - 1) {
            Log.d(TAG, "onBindViewHolder: Chat Message ?" + chat.getMessage());
            Log.d(TAG, "onBindViewHolder: Chat is Seen ?" + mChat.get(position).isSeen());

            if(chat.isSeen()) {
                holder.txt_seen.setText("seen");
            } else {
                holder.txt_seen.setText("delivered");
            }
        } else {
            holder.txt_seen.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemViewType(int position) {
        fuser = FirebaseAuth.getInstance().getCurrentUser();
        if(mChat.get(position).getSender().equals(fuser.getUid()))
            return MSG_TYPE_RIGHT;
        return MSG_TYPE_LEFT;
    }
    
    @Override
    public int getItemCount() {
        return mChat.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView message, txt_seen;
        ImageView profile_image;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            message = itemView.findViewById(R.id.show_message);
            profile_image = itemView.findViewById(R.id.profile_image);
            txt_seen = itemView.findViewById(R.id.txt_seen);
        }
    }
}