package com.sentimentanalysis.SentimentAnalysis;

class Post {
    private String content;
    private String timestamp;
    private String url;
    private double score;

    Post(String content, String timestamp, String url, double score) {
        this.content = content;
        this.timestamp = timestamp;
        this.url = url;
        this.score = score;
    }

    public String getContent() {
        return content;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public String getUrl() {
        return url;
    }

    public double getScore() {
        return score;
    }
}
