package chinh.app.whatapps.fragment;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import chinh.app.whatapps.FindUsersActivity;
import chinh.app.whatapps.R;

public class fragment_sms extends Fragment implements View.OnClickListener{
    private Button mFindUsers;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_frament_sms,null);
        mFindUsers = (Button) view.findViewById(R.id.findUsers);
        mFindUsers.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.findUsers:
                //Log.d("40","intent");
                Intent intent= new Intent(getActivity(), FindUsersActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }
}
