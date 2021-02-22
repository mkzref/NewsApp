package com.example.newsapp.utils;

import android.text.TextUtils;
import com.example.newsapp.model.News;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class Query {

    private Query() {
    }

    public static List<News> NewsData(String requestUrl) {
        URL url = ccreateUrl(requestUrl);

        String jsonResponse = null;
        try {
            jsonResponse = HttpRequest(url);
        } catch (IOException e) {
        }
        List<News> newsList = extractFeatureFromJSON(jsonResponse);
        return newsList;
    }

    private static URL ccreateUrl(String stringUrl) {
        URL url = null;
        try {
            url = new URL(stringUrl);
        } catch (MalformedURLException e) {

        }
        return url;
    }

    private static String HttpRequest(URL url) throws IOException {
        String jsonResponse = "";
        if (url == null) {
            return jsonResponse;
        }

        HttpURLConnection connection = null;
        InputStream stream = null;
        try {
            connection = (HttpURLConnection) url.openConnection();
            connection.setReadTimeout(Cons.READ_TIMEOUT );
            connection.setConnectTimeout(Cons.CONNECT_TIMEOUT);
            connection.setRequestMethod(Cons.REQUEST__GET);
            connection.connect();
            if (connection.getResponseCode() == Cons.SUCCESS_RESPONSE) {
                stream = connection.getInputStream();
                jsonResponse = readFromStream(stream);
            } else {

            }
        } catch (IOException e) {

        } finally {
            if (connection != null) {
                connection.disconnect();
            }
            if (stream != null) {
                stream.close();
            }
        }
        return jsonResponse;
    }


    private static String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader streamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader bufferedReader = new BufferedReader(streamReader);
            String line = bufferedReader.readLine();
            while (line != null) {
                stringBuilder.append(line);
                line = bufferedReader.readLine();
            }
        }
        return stringBuilder.toString();
    }


    private static List<News> extractFeatureFromJSON(String newsJSON) {

        if (TextUtils.isEmpty(newsJSON)) {
            return null;
        }
        List<News> newsList = new ArrayList<>();
        try {
            JSONObject baseJsonResponse = new JSONObject(newsJSON);

            JSONObject responseJsonObject = baseJsonResponse.getJSONObject(Cons.JSON_KEY_RESPONSE);
            JSONArray resultsArray = responseJsonObject.getJSONArray(Cons.JSON_KEY_RESULTS);
            for (int i = 0; i < resultsArray.length(); i++) {

                JSONObject currentNews = resultsArray.getJSONObject(i);
                String webTitle = currentNews.getString(Cons.JSON_KEY_WEB_TITLE);
                String sectionName = currentNews.getString(Cons.JSON_KEY_SECTION_NAME);
                String webPublicationDate = currentNews.getString(Cons.JSON_KEY_WEB_PUBLICATION_DATE);
                String webUrl = currentNews.getString(Cons.JSON_KEY_WEB_URL);
                String author = null;
                if (currentNews.has(Cons.JSON_KEY_TAGS)) {
                    JSONArray tagsArray = currentNews.getJSONArray(Cons.JSON_KEY_TAGS);
                    if (tagsArray.length() != 0) {
                        JSONObject firstTagsItem = tagsArray.getJSONObject(0);
                        author = firstTagsItem.getString(Cons.JSON_KEY_WEB_TITLE);
                    }
                }

                String thumbnail = null;
                String trailText = null;
                if (currentNews.has(Cons.JSON_KEY_FIELD)) {
                    JSONObject fieldsObject = currentNews.getJSONObject(Cons.JSON_KEY_FIELD);
                    if (fieldsObject.has(Cons.JSON_KEY_THUMBNAIL)) {
                        thumbnail = fieldsObject.getString(Cons.JSON_KEY_THUMBNAIL);
                    }
                    if (fieldsObject.has(Cons.JSON_KEY_TRAIL)) {
                        trailText = fieldsObject.getString(Cons.JSON_KEY_TRAIL);
                    }
                }

                News news = new News(webTitle, sectionName, author, webPublicationDate, webUrl, thumbnail, trailText);
                newsList.add(news);
            }
        } catch (JSONException e) {

        }
        return newsList;
    }
}
