package com.limerick.cs4028.help;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class CreatePostActivity extends AppCompatActivity {

    public static FirebaseAuth mAuth;
    private EditText mTitleField;
    private EditText mMessageField;
    private String title, message, uID, userName;
    private FloatingActionButton mSubmitButton;
    final FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference ref = database.getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_post);
        Intent intent = getIntent();
        Bundle bd = intent.getExtras();
        if(bd != null)
        {
            uID = (String) bd.get("com.limerick.cs4028.help.EXTRA_USERNAME");
        }

        DatabaseReference ref = database.getReference("Users");
        ref.child(uID).addListenerForSingleValueEvent(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        // Get the address object
                        User user = dataSnapshot.getValue(User.class);
                        // get the string here, using address.getCity() or whatever you called the getter
                        userName = user.username;
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                    }
                });


        mTitleField = findViewById(R.id.field_title);
        mMessageField = findViewById(R.id.field_body);
        mSubmitButton = findViewById(R.id.fab_submit_post);

        mSubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSubmitButton.setVisibility(View.GONE);
                submitPost();
            }
        });
    }

    public void submitPost(){

        title = mTitleField.getText().toString();
        message = mMessageField.getText().toString();

        DatabaseReference postsRef = ref.child("posts");

        DatabaseReference newPostRef = postsRef.push();
        newPostRef.setValue(new Post(userName, title, message));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_logout:
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(this, MainActivity.class));
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
