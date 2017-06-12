package com.example.imtpmd1617.studievoortgang.Models;


import java.io.Serializable;

public class Token implements Serializable {

    private String key;

    public Token(String key){
        this.key = key;
    }

    public Token(){
    }
}
