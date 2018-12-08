package com.uob.esclient.example;

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

    public static void main(String[] args) throws UnknownHostException {

        ElasticClient client = new ElasticClient();
        SearchQuery searchQuery = SearchQuery.builder().with((sq) -> {
            sq.limit = 100;
            sq.fieldToCompareAgainst = "content";
            sq.strategy = Strategy.FUZZY_MATCH;
            sq.literalQuery = "pythn";
        }).build();

        List<MyPost> posts = client.findPosts(searchQuery, MyPost.class);
        posts.forEach(System.out::println);

        /*
        String clusterId = "e3b8bb93971646e2a03bd86952f68ad0"; // Your cluster ID here
        Settings settings = Settings.builder()
                .put("transport.ping_schedule", "5s")
                //.put("transport.sniff", false) // Disabled by default and *must* be disabled.
                .put("cluster.name", clusterId)
                .put("request.headers.X-Found-Cluster", clusterId)
                .build();

        TransportClient client = new PreBuiltTransportClient(settings).
                addTransportAddress(
                        new TransportAddress(InetAddress.getByName("e3b8bb93971646e2a03bd86952f68ad0.us-central1.gcp.cloud.es.io")
                                , 9343));
        ElasticClient ec = new ElasticClient(client);
        SearchQuery searchQuery = SearchQuery.builder().with((sq) -> {
            sq.limit = 100;
            sq.fieldToCompareAgainst = "content";
            sq.strategy = Strategy.FUZZY_MATCH;
            sq.literalQuery = "pythn";
        }).build();

        ec.findPosts(searchQuery, MyPost.class); */
    }
}
