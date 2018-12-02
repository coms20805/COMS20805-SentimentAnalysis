package com.uob.esclient.example;

import com.uob.esclient.client.ElasticClient;
import com.uob.esclient.search.SearchQuery;
import com.uob.esclient.search.Strategy;

import java.util.Date;
import java.util.List;

import lombok.Value;

/*
A simple demo app illustrating how to use ElasticClient.

NOte: To run this app, you need to download the elasticsearch
binaries from https://www.elastic.co/downloads/elasticsearch.

 After downloading, execute the binaries: `bin/elasticsearch`
 This starts up the low-level elastic search client needed
 for our ElasticClient to work.
 */

public class App {


    /* A generic Post that represents the documents
    stored in the elasticsearch database
    */
    @Value
    private static class MyPost {
        private final String content;
        private final Date timestamp;
        private final String url;
        private final double score;

    }

    public static void main(String[] args) {
        ElasticClient client = new ElasticClient();
        SearchQuery searchQuery = SearchQuery.builder().with((sq) -> {
            sq.limit = 100;
            sq.fieldToCompareAgainst = "content";
            sq.strategy = Strategy.FUZZY_MATCH;
            sq.literalQuery = "pythn";
        }).build();


        List<MyPost> posts = client.findPosts(searchQuery, MyPost.class);
        posts.forEach(System.out::println);
    }
}
