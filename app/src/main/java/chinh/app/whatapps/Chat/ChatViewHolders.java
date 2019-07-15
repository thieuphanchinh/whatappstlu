package chinh.app.whatapps.Chat;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import chinh.app.whatapps.R;

public class ChatViewHolders extends RecyclerView.ViewHolder implements View.OnClickListener {
    //part 17 phan 3 chat view hoder update
    public TextView mMeassage;
    public LinearLayout mContainer;

    //
    public ChatViewHolders(View itemView){
        super(itemView);
        itemView.setOnClickListener(this);

        //part 17
        mMeassage=itemView.findViewById(R.id.message);
        mContainer=itemView.findViewById(R.id.container);
        //

    }
    @Override
    public void onClick(View v) {

    }
}
