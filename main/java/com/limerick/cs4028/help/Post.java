package com.limerick.cs4028.help;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Post {

    //public String author;
    public String title;
    public String message;
    public String author;

    public Post() {
        // Default constructor required for calls to DataSnapshot.getValue(Post.class)
    }
//String author,
    public Post(String author, String title, String message) {
        this.author = author;
        this.title = title;
        this.message = message;
    }
    public String getAuthor(){
        return author;
    }

    public String getTitle() {
        return title;
    }

    public String getMessage() {
        return message;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public String toString(){

        return this.author;
    }
}
