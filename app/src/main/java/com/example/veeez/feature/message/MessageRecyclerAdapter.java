package com.example.veeez.feature.message;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.veeez.R;

public class MessageRecyclerAdapter extends RecyclerView.Adapter<MessageRecyclerAdapter.MessageViewHolder> {
    private UserMessageResponse userMessageResponse;

    public MessageRecyclerAdapter(UserMessageResponse response) {
        userMessageResponse = response;
    }

    @NonNull
    @Override
    public MessageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.user_message_item, parent, false);
        return new MessageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MessageViewHolder holder, int position) {
        holder.bind(userMessageResponse.getMessages().get(position));
    }

    @Override
    public int getItemCount() {
        return userMessageResponse.getMessages().size();
    }

    public class MessageViewHolder extends RecyclerView.ViewHolder {
        TextView messageText;

        public MessageViewHolder(@NonNull View itemView) {
            super(itemView);
            messageText = itemView.findViewById(R.id.messageText);
        }

        public void bind(MessageObject message) {
            messageText.setText(message.getText());
        }
    }
}
