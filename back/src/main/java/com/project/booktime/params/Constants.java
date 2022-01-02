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

    // Google books categories
    String categories[] = {
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
            "Mystery",
            "Manga",
            "Romance",
            "Science",
            "Thriller"
    };
}
