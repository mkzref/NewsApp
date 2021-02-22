package com.example.newsapp.model;

public class News {

    private String mTitle;
    private String mSection;
    private String mAuthor;
    private String mDate;
    private String mUrl;
    private String mThumbnail;
    private String mTrailTextHtml;

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public void setmSection(String mSection) {
        this.mSection = mSection;
    }

    public void setmAuthor(String mAuthor) {
        this.mAuthor = mAuthor;
    }

    public void setmDate(String mDate) {
        this.mDate = mDate;
    }

    public void setmUrl(String mUrl) {
        this.mUrl = mUrl;
    }

    public void setmThumbnail(String mThumbnail) {
        this.mThumbnail = mThumbnail;
    }

    public void setmTrailTextHtml(String mTrailTextHtml) {
        this.mTrailTextHtml = mTrailTextHtml;
    }

    public News(String title, String section, String author, String date, String url, String thumbnail, String trailText) {
        mTitle = title;
        mSection = section;
        mAuthor = author;
        mDate = date;
        mUrl = url;
        mThumbnail = thumbnail;
        mTrailTextHtml = trailText;
    }
    public String getUrl() {
        return mUrl;
    }
    public String getTitle() {
        return mTitle;
    }

    public String getSection() {
        return mSection;
    }

    public String getThumbnail() {
        return mThumbnail;
    }

    public String getTrailTextHtml() {
        return mTrailTextHtml;
    }

    public String getAuthor() {
        return mAuthor;
    }

    public String getDate() {
        return mDate;
    }
}
