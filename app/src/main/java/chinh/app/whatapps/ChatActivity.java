package chinh.app.whatapps;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import chinh.app.whatapps.Chat.ChatAdapter;
import chinh.app.whatapps.Chat.ChatObject;

public class ChatActivity extends AppCompatActivity {
    //private String matchId;
    private String currentphone;
    private String currentUserId;
    DatabaseReference mDatabaseUser,mDatabaseChat;

    private String chatId;

    private ArrayList<ChatObject> resultChat= new ArrayList<ChatObject>();
    private RecyclerView.Adapter mChatAdapter;

    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mChatLayoutManager;
    private List<ChatObject> getDataSetMatches() {
        return resultChat;
    }

    private EditText mSendEditText;
    private ImageButton mSendButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        //matchId = getIntent().getExtras().getString("matchId");
        getFireBase(); ///ham nay sai
        getChatId();
        getAdaper();
        setButton();
    }

    private void setButton() {
        mSendEditText=findViewById(R.id.message);
        mSendButton=findViewById(R.id.send);
        mSendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMessage();
            }
        });
    }

    private void sendMessage() {
        String sendMessageText = mSendEditText.getText().toString();
        if (!sendMessageText.isEmpty()){
            DatabaseReference newMessageDb = mDatabaseChat.push();


            Map newMessage = new HashMap();
            newMessage.put("createdByUser",currentUserId);
            newMessage.put("text",sendMessageText);
            newMessageDb.setValue(newMessage);
            Log.d("ChatActivity","95");
        }
        mSendEditText.setText(null);
    }

    private void getAdaper() {
        mRecyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        mRecyclerView.setNestedScrollingEnabled(false);
        mRecyclerView.setHasFixedSize(false);

        mChatLayoutManager = new LinearLayoutManager(ChatActivity.this);
        mRecyclerView.setLayoutManager(mChatLayoutManager);
        mChatAdapter = new ChatAdapter(getDataSetMatches(),ChatActivity.this);
        mRecyclerView.setAdapter(mChatAdapter);
    }

    private void getChatId() {
        mDatabaseUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){
                    chatId = dataSnapshot.getValue().toString();
                    mDatabaseChat = mDatabaseChat.child(chatId);
                    Log.d("107","107");
                    //part 17
                    getChatMessages();
                    Log.d("110","110");
                    //
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void getChatMessages() {
        mDatabaseChat.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                if (dataSnapshot.exists()){
                    String message = null;
                    String createdByUser = null;

                    if (dataSnapshot.child("text").getValue()!=null){
                        message=dataSnapshot.child("text").getValue().toString();

                    }
                    Log.d("Chat","134");
                    if (dataSnapshot.child("createdByUser").getValue()!=null){
                        createdByUser=dataSnapshot.child("createdByUser").getValue().toString();

                    }

                    if (message!=null && createdByUser!=null){
                        Log.d("141","141");
                        Boolean currentUserBoolean=false;
                        if (createdByUser.equals(currentUserId)){
                            currentUserBoolean=true;
                        }
                        ChatObject newMessage = new ChatObject(message,currentUserBoolean);
                        resultChat.add(newMessage);
                        mChatAdapter.notifyDataSetChanged();
                        Log.d("148","148");
                    }
                }
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void getFireBase() {
        //lay so phone nguoi dung hien tai
        //currentUserId = FirebaseAuth.getInstance().getCurrentUser().getUid();

        //*******ham nay sai
        currentphone = FirebaseAuth.getInstance().getCurrentUser().getUid();

        //???
        //currentUserId phai == so dien thoai cua thang dang dang nhap
        //mDatabaseUser = FirebaseDatabase.getInstance().getReference().child("USERS").child(currentUserId).child(matchId).child("ChatId");
        mDatabaseUser = FirebaseDatabase.getInstance().getReference().child("USERS").child(currentphone).child("chat");

        //lay bang chat ok ko can sua
        mDatabaseChat = FirebaseDatabase.getInstance().getReference().child("CHAT");
    }
    }

