package com.uob.esclient.search;

import com.uob.esclient.post.Post;

import org.elasticsearch.client.transport.TransportClient;

import java.util.List;

public class StringMatcher implements Matcher {
    private TransportClient client;

    public StringMatcher(TransportClient client) {
        this.client = client;
    }

    private buildQuery() {

    }


    @Override
    public List<Post> findPostsClosestTo(String query) {
    }

    @Override
    public List<Post> findPostsClosestTo(String query, int limit) {
        return findPostsClosestTo(query);
    }
}
