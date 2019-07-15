package chinh.app.whatapps;

import android.Manifest;
import android.content.Intent;
import android.database.Cursor;
import android.os.Build;
import android.provider.ContactsContract;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import chinh.app.whatapps.fragment.ViewPagerAdapter;
import chinh.app.whatapps.fragment.fragment;
import chinh.app.whatapps.fragment.fragment_frend;
import chinh.app.whatapps.fragment.fragment_sms;

public class HomeActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    //Part 7
    private DatabaseReference usersDb;
    private String currentUid;

    Toolbar toobar;
    TabLayout tabLayout;
    ViewPager viewpager;
    //ViewPager viewpager;
    private  int[] tabIcon = {
            R.drawable.chat,
            R.drawable.home,
            R.drawable.like,
    };
    private Button mFindUsers;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        toobar = findViewById(R.id.toobar);
        setSupportActionBar(toobar);
        toobar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        toobar.setNavigationOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View v) { startActivity(new Intent(getApplicationContext(),LoginActivity.class));

            }});

        tabLayout = findViewById(R.id.tabs);
        viewpager = findViewById(R.id.viewpager);

        setupViewPager(viewpager);
        tabLayout.setupWithViewPager(viewpager);
        setupIcon();



        //getPermissions();
        //getContactList();



    }

    private void setupViewPager(ViewPager viewpager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment( new fragment_sms(),"Nhắn tin");

        adapter.addFragment(new fragment_frend(), "Bạn bè");
        adapter.addFragment(new fragment(), "Three");
        viewpager.setAdapter(adapter);
        viewpager.setOffscreenPageLimit(3);
    }





    private void setupIcon() {
        tabLayout.getTabAt(0).setIcon(tabIcon[0]);
        tabLayout.getTabAt(1).setIcon(tabIcon[1]);
        tabLayout.getTabAt(2).setIcon(tabIcon[2]);
    }

    private void setupFireBase() {
        mAuth= FirebaseAuth.getInstance();
        //part7
        usersDb= FirebaseDatabase.getInstance().getReference().child("Users");
        currentUid = mAuth.getCurrentUser().getUid();
    }
    
    
}
