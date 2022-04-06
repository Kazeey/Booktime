package com.project.booktime.params;

import org.apache.tomcat.util.bcel.Const;

public class Constants
{
    private Constants() {}

    // API Key
    public final static String API_KEY_GOOGLE_BOOKS = "AIzaSyDojgOQt_GHgwTR-bCM--As_WTRERNqz0k";

    // URL
    public final static String BACK_URL = "http://127.0.0.1:8080/";
    public final static String EXEMPLE_CONTROLLER_URL = "exemples/controller";
    public final static String DATA_COLLECT_URL = "private/controllers/dataCollect";
    public final static String GOOGLE_API = "https://www.googleapis.com/books/v1/volumes?q=";
    public final static String NON_ACQUIS = "N/A";

    public final static String USER_NOT_FOUND = "Il n'y a pas d'utilisateur avec cet email.";

    // Google books categories
    public final static String categories[] = {
            "Anthologies",
            "Art",
            "Biographies",
            "Business",
            "Children",
            "Comics",
            "Contemporary",
            "Crime",
            "Engineering",
            "Fantasy",
            "Fiction",
            "History",
            "Horror",
            "Humor",
            "Litterature",
            "Love",
            "Mystery",
            "Manga",
            "Romance",
            "Science",
            "Thriller"
    };
}
