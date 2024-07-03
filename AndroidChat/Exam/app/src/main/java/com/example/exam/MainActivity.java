package com.example.exam;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.database.FirebaseListAdapter;
import com.firebase.ui.database.FirebaseListOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import android.text.format.DateFormat;

public class MainActivity extends AppCompatActivity {

    private static int SIGN_IN_CODE = 1;
    private RelativeLayout activity_main;
    private FirebaseListAdapter<Message> adapter;

    private FloatingActionButton senbBtn;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == SIGN_IN_CODE) {
            if (requestCode == RESULT_OK) {
                Toast.makeText(getApplicationContext(), "Вы авторизoваны", Toast.LENGTH_LONG).show();
                displayAllMessages();
            } else {
                Toast.makeText(getApplicationContext(), "Вы не авторизoваны", Toast.LENGTH_LONG).show();
                finish();
            }

        }
    }
    @Override
    protected void onStart(){
        super.onStart();
        if (adapter!=null){
            adapter.startListening();
        }
    }
    @Override
    protected void onStop(){
        super.onStop();
        if (adapter!=null) {
            adapter.stopListening();
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       // activity_main = findViewById(R.id.activity_main);
        senbBtn = findViewById(R.id.btnSend);
        //senbBtn.findViewById(R.id.btnSend);
        senbBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText textField = findViewById(R.id.messageField);
                if(textField.getText().toString() == "")
                    return;
                FirebaseDatabase.getInstance().getReference().push().setValue(
                        new Message(FirebaseAuth.getInstance().getCurrentUser().getEmail(),
                                textField.getText().toString()));
                textField.setText("");
            }
        });

        if (FirebaseAuth.getInstance().getCurrentUser() == null) {
            startActivityForResult(AuthUI.getInstance().createSignInIntentBuilder().build(), SIGN_IN_CODE);
        } else {
            Toast.makeText(getApplicationContext(), "Вы авторизoваны", Toast.LENGTH_LONG).show();
            displayAllMessages();
        }
    }


    private void displayAllMessages() {
        ListView listOfMessages = findViewById(R.id.list_of_messages);
        FirebaseListOptions<Message> options = new FirebaseListOptions.Builder<Message>()
                .setQuery(FirebaseDatabase.getInstance().getReference(), Message.class)
                .setLayout(R.layout.list_item)
                .build();
        adapter = new FirebaseListAdapter<Message>(options) {
            @Override
            protected void populateView(@NonNull View v, @NonNull Message model, int position) {
                TextView mess_user, mess_time, mess_text;
                mess_user = v.findViewById(R.id.message_user);
                mess_time = v.findViewById(R.id.message_time);
                mess_text = v.findViewById(R.id.message_text);

                mess_user.setText(model.getUserName());
                mess_text.setText(model.getTextMessage());
                mess_time.setText(DateFormat.format("dd-mm-yyyy HH:MM:ss", model.getMessageTime()));
            }
        };
        listOfMessages.setAdapter(adapter);

    }
}
