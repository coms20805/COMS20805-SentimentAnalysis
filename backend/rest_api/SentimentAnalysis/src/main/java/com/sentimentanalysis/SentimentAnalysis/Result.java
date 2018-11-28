package com.sentimentanalysis.SentimentAnalysis;

import java.util.List;

public class Result {
    private float rating;
    private List<Post> posts;

    public Result(float rating, List<Post> posts) {
        this.rating = rating;
        this.posts = posts;
    }

    public float getRating() {
        return rating;
    }

    public List<Post> getPosts() {
        return posts;
    }
}
