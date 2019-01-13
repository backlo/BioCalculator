package com.example.lenovo.bio_calculator;

public class Event {

    public final int data;
    public String nickname;

    public Event(int data){
        this.data = data;
    }

    public Event(int data, String nickname){
        this.data = data;
        this.nickname = nickname;
    }
}
