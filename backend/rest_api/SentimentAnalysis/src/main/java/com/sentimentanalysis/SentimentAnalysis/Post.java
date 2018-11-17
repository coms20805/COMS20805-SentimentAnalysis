package com.sentimentanalysis.SentimentAnalysis;

import java.util.Date;

public class Post {
    private float score;
    private String content;
    private String url;
    private Date timestamp;

    public Post(float score, String content, String url, Date timestamp) {
        this.score = score;
        this.content = content;
        this.url = url;
        this.timestamp = timestamp;
    }

    public float getScore() {
        return score;
    }

    public String getContent() {
        return content;
    }

    public String getUrl() {
        return url;
    }

    public Date getTimestamp() {
        return timestamp;
    }
}
