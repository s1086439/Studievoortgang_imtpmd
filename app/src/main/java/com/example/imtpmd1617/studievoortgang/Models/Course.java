package com.example.imtpmd1617.studievoortgang.Models;

import java.io.Serializable;

public class Course implements Serializable {

    public String period;
    public String name;
    public String ects;
    public String grade;

    public Course(String courseName, String ects, String grade, String period){
        this.name = courseName;
        this.ects = ects;
        this.grade = grade;
        this.period = period;
    }

    public Course(){
    }
}
