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

    public final static String MAIL_HOST = "smtp.gmail.com";
    public final static int MAIL_PORT = 587;
    public final static String MAIL_USERNAME = "booktime.projet@gmail.com";
    public final static String MAIL_PASSWORD = "Booktime1!";

    public static final long ACCESS_TOKEN_VALIDITY_SECONDS = 5*60*60;
    public static final String SIGNING_KEY = "booktime";

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
