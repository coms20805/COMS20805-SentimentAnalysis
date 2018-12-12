package com.sentimentanalysis.SentimentAnalysis;

import java.util.Date;

class Post {
    private String content;
    private int timestamp;
    private String url;
    private double score;

    Post(String content, int timestamp, String url, double score) {
        this.content = content;
        this.timestamp = timestamp;
        this.url = url;
        this.score = score;
    }

    public String getContent() {
        return content;
    }

    public int getTimestamp() {
        return timestamp;
    }

    public String getUrl() {
        return url;
    }

    public double getScore() {
        return score;
    }
}
