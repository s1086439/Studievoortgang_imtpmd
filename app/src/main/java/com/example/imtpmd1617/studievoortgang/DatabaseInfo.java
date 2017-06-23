package com.example.imtpmd1617.studievoortgang;


public class DatabaseInfo {

    // Namen van de tabellen die moeten worden aangemaakt

    public class Tables {
        public static final String MODULES = "modules";
        public static final String STUDENTEN = "studenten";
    }

    // Functie voor het aanmaken van de kolommen in SQLite

    public class ModulesColumn {
        public static final String ID = "id";
        public static final String CIJFER = "cijfer";
        public static final String NAAM = "module_naam";
        public static final String AFKORTING = "module_afkorting";
        public static final String ECT = "ect";
        public static final String PERIODE = "periode";
        public static final String FASE = "fase";
    }

    // Functie voor het aanmaken van de kolommen in SQLite

    public class StudentColumn {
        public static final String STUDENTID = "studentId";
        public static final String TOKEN = "token";
        public static final String VOORNAAM = "voornaam";
        public static final String ACHTERNAAM = "achternaam";
        public static final String STUDIERICHTING = "studierichting";
    }
}