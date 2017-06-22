package com.example.imtpmd1617.studievoortgang.Models;


public class Student{

    public int studentId;
    public String voornaam, achternaam, token, studierichting;

    public Student(){}

    public Student(int studentId, String voornaam, String achternaam, String studierichting) {
        this.studentId = studentId;
        this.voornaam = voornaam;
        this.achternaam = achternaam;
        this.studierichting = studierichting;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentnummer) {
        this.studentId = studentId;
    }

    public String getVoornaam() {
        return voornaam;
    }

    public void setVoornaam(String voornaam) {
        this.voornaam = voornaam;
    }

    public String getAchternaam() {
        return achternaam;
    }

    public void setAchternaam(String achternaam) {
        this.achternaam = achternaam;
    }

    public String getStudierichting() {
        return studierichting;
    }

    public void setStudierichting(String studierichting) {
        this.studierichting = studierichting;
    }
}
