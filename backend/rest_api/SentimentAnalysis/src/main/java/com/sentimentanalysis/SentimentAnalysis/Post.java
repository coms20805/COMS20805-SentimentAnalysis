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

    String getContent() {
        return content;
    }

    int getTimestamp() {
        return timestamp;
    }

    String getUrl() {
        return url;
    }

    double getScore() {
        return score;
    }
}
