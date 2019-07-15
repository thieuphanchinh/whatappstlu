package chinh.app.whatapps.Chat;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;



import java.util.List;

import chinh.app.whatapps.R;

public class ChatAdapter extends RecyclerView.Adapter<ChatViewHolders>  {
    private List<ChatObject> chatList;
    private Context context;

    public ChatAdapter(List<ChatObject> ChatList, Context context){
        this.chatList=ChatList;
        this.context=context;
    }

    @Override
    public ChatViewHolders onCreateViewHolder(ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_chat,null,false);
        RecyclerView.LayoutParams lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutView.setLayoutParams(lp);
        ChatViewHolders rcv = new ChatViewHolders((layoutView));


        return rcv;
    }

    @Override
    public void onBindViewHolder(ChatViewHolders holder, int position) {
        //part 17
        holder.mMeassage.setText(chatList.get(position).getMessage());
        if (chatList.get(position).getCurrentUser()){
            holder.mMeassage.setGravity(Gravity.END);
//            holder.mMeassage.setTextColor(Color.parseColor("#404040"));
//            holder.mContainer.setBackgroundColor(Color.parseColor("#F4F4F4"));
            //holder.mMeassage.setBackgroundResource(R.drawable.my_message);
        }
        else
        {
            holder.mMeassage.setGravity(Gravity.START);
            holder.mMeassage.setTextColor(Color.parseColor("#FFFFFF"));
            holder.mContainer.setBackgroundColor(Color.parseColor("#2DB4C8"));

            //holder.mMeassage.setBackgroundResource(R.drawable.their_message);
        }
        //
    }

    @Override
    public int getItemCount() {
        return chatList.size();
    }
}
