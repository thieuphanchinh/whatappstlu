package chinh.app.whatapps;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class LoginActivity extends AppCompatActivity {

    private Button mLogin;
    private EditText mPhone, mPassword;

//    private FirebaseAuth mAuth;
//    private FirebaseAuth.AuthStateListener firebaseAuthStateListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mPhone=findViewById(R.id.loginPhoneNumber);
        mPassword=findViewById(R.id.loginPass);
        mLogin=findViewById(R.id.nextBtn);

        mLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String phone = mPhone.getText().toString();
                final String password = mPassword.getText().toString();
                if (phone.equals("")) {
                    Toast.makeText(LoginActivity.this, "Bạn chưa nhập số điện thoại",Toast.LENGTH_SHORT).show();
                }
                else if (password.equals("")){
                    Toast.makeText(LoginActivity.this, "Mật khẩu sai",Toast.LENGTH_SHORT).show();
                }
                else {
                    final ArrayList<String> listphone=new ArrayList<>();
                    final DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("USERS");
                    ref.addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {
                                    //Get map of users in datasnapshot
                                    listphone.add(ref.getKey());

                                }

                                @Override
                                public void onCancelled(DatabaseError databaseError) {
                                    //handle databaseError
                                }
                            });
//                    Intent intent = new Intent(LoginActivity.this,HomeActivity.class);
//                    startActivity(intent);
//                    finish();
                    for (String s:listphone){
                        Log.d("72",s);
                    }
                }





            }
        });
    }
}
