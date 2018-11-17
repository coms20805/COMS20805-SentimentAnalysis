package com.sentimentanalysis.SentimentAnalysis;

import java.util.ArrayList;

public class Result {
    private float rating;
    private ArrayList<Post> posts;

    public Result(float rating, ArrayList<Post> posts) {
        this.rating = rating;
        this.posts = posts;
    }

    public float getRating() {
        return rating;
    }

    public ArrayList<Post> getPosts() {
        return posts;
    }
}
