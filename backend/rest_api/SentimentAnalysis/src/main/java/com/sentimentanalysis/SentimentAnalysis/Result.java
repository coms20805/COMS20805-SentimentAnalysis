package com.sentimentanalysis.SentimentAnalysis;

import java.util.List;

public class Result {
    private double rating;
    private List<Post> posts;

    Result(double rating, List<Post> posts) {
        this.rating = rating;
        this.posts = posts;
    }

    public double getRating() {
        return rating;
    }

    public List<Post> getPosts() {
        return posts;
    }
}
