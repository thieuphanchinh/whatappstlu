package chinh.app.whatapps.Adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import chinh.app.whatapps.Model.User;
import chinh.app.whatapps.R;

// dang sai

public class UserListAdapter extends RecyclerView.Adapter<UserListAdapter.MyViewHolder> {
    private ArrayList<User> mDataset;
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView textView;
        public MyViewHolder(TextView v) {
            super(v);
            textView = v;
        }

    }
    public UserListAdapter(ArrayList<User> myDataset) {
        mDataset = myDataset;
    }

    @NonNull
    @Override
    public UserListAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        TextView v;
        v = (TextView) LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.activity_find_users,viewGroup,false);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull UserListAdapter.MyViewHolder myViewHolder, int i) {
        //myViewHolder.textView.setText(mDataset.indexOf().);
    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
