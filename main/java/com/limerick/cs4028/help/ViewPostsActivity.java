package com.limerick.cs4028.help;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import java.util.List;


public class ViewPostsActivity extends AppCompatActivity {

    final FirebaseDatabase database = FirebaseDatabase.getInstance();
    private TextView authorTV, titleTV, bodyTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        authorTV = findViewById(R.id.authorTV);
        titleTV = findViewById(R.id.titleTV);
        bodyTV = findViewById(R.id.bodyTV);

        DatabaseReference databaseReference = database.getReference("posts");
        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String prevChildKey) {
                Post post = dataSnapshot.getValue(Post.class);
                authorTV.setText(post.author);
                titleTV.setText(post.title);
                bodyTV.setText(post.message);
            }
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {}
            public void onChildRemoved(DataSnapshot dataSnapshot) {}
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {}
            public void onCancelled(DatabaseError databaseError) {}
        });
//        tv.setText(test.get(0));


        //tv.setText(posts.get(0).author);
//        ArrayAdapter<String> listAdapter = new ArrayAdapter<>(
//                this,
//                android.R.layout.simple_list_item_1, test
//        );
//        ListView postListView = findViewById(R.id.posts);
//        postListView.setAdapter(listAdapter);


//        ArrayAdapter<Post> listAdapter = new ArrayAdapter<>(
//                this,
//                android.R.layout.simple_list_item_1, posts
//        );
//        ListView postListView = findViewById(R.id.posts);
//        postListView.setAdapter(listAdapter);

//        AdapterView.OnItemClickListener onItemClickListener = new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
//                Intent intent = new Intent(ViewPostsActivity.this, MainActivity.class);
//                //intent.putExtra("com.kumballz.brian.EXTRA_BUILDINGID", (int) id);
//                startActivity(intent);
//            }
//        };
//        postListView.setOnItemClickListener(onItemClickListener);
    }
}
