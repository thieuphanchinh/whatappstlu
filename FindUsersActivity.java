package chinh.app.whatapps;

import android.Manifest;
import android.database.Cursor;
import android.os.Build;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import chinh.app.whatapps.Adapter.UserListAdapter;
import chinh.app.whatapps.Model.User;

public class FindUsersActivity extends AppCompatActivity{
    ArrayList<String> listphone=new ArrayList<>();


    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_users);
        final ArrayList<User> contactList;
        final ArrayList<User> myDataset = null;
        contactList= new ArrayList<>();

        getPermissions();

        Cursor phones = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, null);
        while(phones.moveToNext()){
            String name = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
            String phone = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));

            phone = phone.replace(" ", "");
            phone = phone.replace("-", "");
            phone = phone.replace("(", "");
            phone = phone.replace(")", "");
            phone = phone.replace("+", "");

            if(phone.charAt(0)=='8'&&phone.charAt(1)=='4') {
                phone = phone.substring(2, phone.length());
                phone = '0'+phone;
            }

//            for (String s:listpho)
            User mContact = new User(phone, name);
            contactList.add(mContact);
            //getUserDetails(mContact);
            for (User user:contactList){
                Log.d("44",user.getPhone());
                Log.d("44",user.getName());
            }
        }
        recyclerView = (RecyclerView) findViewById(R.id.userList);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true);

        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        mAdapter = new UserListAdapter(myDataset);
        // specify an adapter (see also next example)


        DatabaseReference currentUserDb = FirebaseDatabase.getInstance().getReference().child("USERS");
        currentUserDb.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for(User user:contactList){
                    //I am not sure what record are you specifically looking for
                    //This is if you are getting the Key which is the record ID for your Coupon Object
                    for (DataSnapshot val : dataSnapshot.getChildren()){
                        String phonetmp=user.getPhone();
                        if(val.getKey().equals(phonetmp)){
                            listphone.add(phonetmp);
                            Log.d("77",phonetmp);
                            User user1=new User(phonetmp,user.getName());
                            myDataset.add(user1);

                        }
                    }


                    //This is if your are querying for the hotel child
//                                        if(val.child("hotel").getValue(String.class).contains("Hotel")){
//                                            //Do what you want with the record
//                                        }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        //Log.d("91", ""+listphone.size());
//        for (String s:listphone){
//            Log.d("93",s);
//        }

        recyclerView.setAdapter(mAdapter);


    }

    public void getListPhoneNow() {

//        DatabaseReference currentUserDb = FirebaseDatabase.getInstance().getReference().child("USERS");
//        Map userIDtmp = new HashMap<>();
//        userIDtmp.put("phone","000");
//        Map userInfotmp = new HashMap<>();
//        //userInfo.put("phone", phone);
//        userInfotmp.put("name", "00x");
//        currentUserDb.child("000").updateChildren(userInfotmp);
//        currentUserDb.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                if (dataSnapshot.exists()) {
//                    int i=0;
//                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
//                        String phonexxx = postSnapshot.getKey();
//                        if (i<listphone.size()) {
//                            if (!phonexxx.equals(listphone.get(i).toString())) {
//                                listphone.add(phonexxx);
//                            }
//                            i++;
//                        }
//                        else  listphone.add(phonexxx);
//
//                    }
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });

        //DatabaseReference refdb = FirebaseDatabase.getInstance().getReference().child("USERS");




    }


    private void getPermissions() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(new String[]{Manifest.permission.WRITE_CONTACTS, Manifest.permission.READ_CONTACTS}, 1);
        }
    }
    private void getContactList() {



    }
}
