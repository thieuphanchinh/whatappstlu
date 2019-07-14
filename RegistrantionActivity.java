package chinh.app.whatapps;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseError;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RegistrantionActivity extends AppCompatActivity {
    private Button mRegister;
    private EditText mPhone, mPassword, mName;
    private RadioGroup mRadioGroup;
    private FirebaseAuth mAuth;
    private boolean checklogin=false;

    public static ArrayList<String> listphone = new ArrayList<>();
    public static ArrayList<String> getListPhone(){
        return listphone;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrantion);



        mRegister=(Button) findViewById(R.id.register);
        mPhone=(EditText)findViewById(R.id.phone);
        mPassword=(EditText)findViewById(R.id.password);
        mName=(EditText)findViewById(R.id.name);
        mRadioGroup=(RadioGroup)findViewById(R.id.radioGroup);


        mRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String phone = mPhone.getText().toString();
                final String password = mPassword.getText().toString();
                final String name = mName.getText().toString();
                if (name.equals("")) {
                    Toast.makeText(RegistrantionActivity.this, "Bạn chưa nhập tên",Toast.LENGTH_SHORT).show();
                }else
                {
                    int selectId=-1;
                    switch (mRadioGroup.getCheckedRadioButtonId()) {
                        case R.id.radioMale:
                            selectId = 0;
                            break;
                        case R.id.radioFemale:
                            selectId = 1;
                            break;
                    }
                    // Check which radio button was clicked
                    if (selectId==-1) {

                        Toast.makeText(RegistrantionActivity.this,"Bạn chưa chọn giới tính",Toast.LENGTH_SHORT).show();

                    }else {
                        int newselectId = mRadioGroup.getCheckedRadioButtonId();
                        final RadioButton radioButton = (RadioButton) findViewById(newselectId);
                        if (radioButton.getText() == null) {
                            return;
                        }
                        if (phone.equals("")) {
                            Toast.makeText(RegistrantionActivity.this, "Bạn chưa nhập số điện thoại",Toast.LENGTH_SHORT).show();
                        }else
                        if (password.equals("")){
                            Toast.makeText(RegistrantionActivity.this, "Bạn chưa nhập mật khẩu",Toast.LENGTH_SHORT).show();
                        }
                        else {

                            final boolean[] maketoast = {true};
                            DatabaseReference currentUserDb = FirebaseDatabase.getInstance().getReference().child("USERS");

                            currentUserDb.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                    boolean kt1=true;
                                    for(DataSnapshot val : dataSnapshot.getChildren()){
                                        Log.d("103key",val.getKey());
                                        if(val.getKey().equals(phone)){
                                            kt1 =false;
                                            maketoast[0] =false;
                                            break;
                                        }

                                    }
                                    if (kt1) {
                                        DatabaseReference currentUserDb = FirebaseDatabase.getInstance().getReference().child("USERS");
                                        Map userID = new HashMap<>();
                                        userID.put("phone",phone);
                                        Map userInfo = new HashMap<>();
                                        userInfo.put("name", name);
                                        userInfo.put("pass", password);
                                        userInfo.put("profileImageUrl", "default");
                                        //part 12
                                        String sex = "";
                                        if (radioButton.getText().toString().equals("Nữ"))
                                            sex = "Female";
                                        if (radioButton.getText().toString().equals("Nam"))
                                            sex = "Male";
                                        userInfo.put("sex", sex);
                                        currentUserDb.child(phone).updateChildren(userInfo);

                                        Intent intent = new Intent(RegistrantionActivity.this,HomeActivity.class);
                                        startActivity(intent);
                                        finish();
                                    }

                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });

                            if (maketoast[0]){
                                Toast.makeText(RegistrantionActivity.this,"Số điện thoại tồn tại",Toast.LENGTH_SHORT).show();
                            }



                        }
                    }


                }
            }


        });

        ImageButton backButton = (ImageButton)findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegistrantionActivity.this,MainActivity.class);
                startActivity(intent);
                finish();

            }
        });
    }
}
