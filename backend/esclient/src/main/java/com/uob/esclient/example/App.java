package com.uob.esclient.esqample;

import com.uob.esclient.client.ElasticClient;
import com.uob.esclient.post.Post;
import com.uob.esclient.search.SearchQuery;
import com.uob.esclient.search.Strategy;

import java.util.List;

public class App {

    public static void main(String[] args) {
        ElasticClient cl = new ElasticClient();
        SearchQuery searchQuery = SearchQuery.builder().with((sq) -> {
            sq.limit = 100;
            sq.fieldToCompareAgainst = "content";
            sq.strategy = Strategy.FUZZY_MATCH;
            sq.literalQuery = "scala";
        }).build();

        List<Post> posts = cl.findPosts(searchQuery, Post.class);
        posts.forEach(System.out::println);
    }
}