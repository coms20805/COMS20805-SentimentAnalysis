package com.uob.esclient.example;

import com.google.gson.Gson;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.uob.esclient.client.ElasticClient;
import com.uob.esclient.search.SearchQuery;
import com.uob.esclient.search.Strategy;

import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;
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
    private static class Post {
        private final String content;
        private final int timestamp;
        private final String url;
        private final double score;

    }

    public static void main(String[] args) throws UnknownHostException, UnirestException {

        /*
        ---  Depreceated ----

        ElasticClient client = new ElasticClient();
        SearchQuery searchQuery = SearchQuery.builder().with((sq) -> {
            sq.limit = 100;
            sq.fieldToCompareAgainst = "content";
            sq.strategy = Strategy.FUZZY_MATCH;
            sq.literalQuery = "pythn";
        }).build();

        List<Post> posts = client.findPosts(searchQuery, Post.class);
        posts.forEach(System.out::println);

        */

        HttpResponse<JsonNode> jsonNodeHttpResponse = Unirest.get("https://es-app.herokuapp.com/query").
                queryString("literal_query", "scala"). //can alternatively pass a map
                asJson();

        String stringifiedJsonList = jsonNodeHttpResponse.getBody().getObject().get("result").toString();
        Gson gson = new Gson();
        Post _posts[] = gson.fromJson(stringifiedJsonList, Post[].class);
        Arrays.stream(_posts).forEach(System.out::println);
    }
}
