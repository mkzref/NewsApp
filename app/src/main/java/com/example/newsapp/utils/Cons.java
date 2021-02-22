package com.example.newsapp.utils;

/*
Author @ Marwa Hatamleh
 */
public class Cons {

    public static final String ORDER = "order-by";
    public static final String PAGE_SIZE = "page-size";
    public static final String ORDER_DATE = "order-date";
    public static final String NEWS_REQUEST = "https://content.guardianapis.com/search";
    public static final String QUERY_PARAM = "q";
    public static final String FORMAT_PARAM = "format";
    public static final String FROM_DATE_PARAM = "from-date";
    public static final String SHOW_FIELDS_PAR = "show-fields";

    public static final String SHOWPARAM = "show-tags";
    public static final String SHOW_FIELDS = "thumbnail,trailText";
    public static final String SECTION = "section";
    public static final String API_KEY_PARAM = "api-key";
    public static final String FORMAT = "json";
    public static final String SHOW = "contributor";
    public static final String API_KEY = "2bbbc59c-5b48-46a5-83d3-8435d3136348";

    public static final int HOME = 0;
    public static final int WORLD = 1;
    public static final int SCIENCE = 2;
    public static final int SPORT = 3;
    public static final int ENVIRONMENT = 4;
    public static final int SOCIETY = 5;
    public static final int FASHION = 6;
    public static final int BUSINESS = 7;
    public static final int CULTURE = 8;
    public static final int DEFAULT_NUMBER = 0;

    static final String JSON_KEY_RESPONSE = "response";
    static final String JSON_KEY_RESULTS = "results";
    static final String JSON_KEY_WEB_TITLE = "webTitle";
    ///////////////////////////////////////////////
    static final String JSON_KEY_SECTION_NAME = "sectionName";
    static final String JSON_KEY_WEB_PUBLICATION_DATE = "webPublicationDate";
    static final String JSON_KEY_WEB_URL = "webUrl";
    /////////////////////////////////////////////
    static final String JSON_KEY_TAGS = "tags";
    static final String JSON_KEY_FIELD = "fields";
    static final String JSON_KEY_THUMBNAIL = "thumbnail";
    static final String JSON_KEY_TRAIL = "trailText";
    static final int READ_TIMEOUT = 10000;

    static final int CONNECT_TIMEOUT = 15000;
    static final int SUCCESS_RESPONSE = 200;
    static final String REQUEST__GET = "GET";
    private Cons() {
    }

}
