package com.library_management_system.entities;

import java.util.UUID;

public class Book {

    private String id;
    private String title;
    private String author;
    private boolean isAvailable;

    public Book(String title, String author) {
        this.title = title;
        this.author = author;
        this.id= UUID.randomUUID().toString();
        isAvailable = true;
    }


    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;

    }
    public boolean isAvailable(){
        return isAvailable;
    }
    public void setAvailable(boolean available){
        this.isAvailable=available;
    }
}