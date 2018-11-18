package com.uob.esclient;

import com.uob.esclient.factory.ClientFactory;
import com.uob.esclient.post.Post;
import com.uob.esclient.search.FuzzyMatcher;
import com.uob.esclient.search.Matcher;
import com.uob.esclient.search.Strategy;
import com.uob.esclient.search.StringMatcher;

import org.elasticsearch.client.transport.TransportClient;

import java.net.UnknownHostException;
import java.util.List;

import lombok.Cleanup;


public final class ElasticClient {
    private final TransportClient transportClient;

    public ElasticClient() {
        this.transportClient = ClientFactory.createClient();
    }

    public ElasticClient(int port, String host) throws UnknownHostException {
        this.transportClient = ClientFactory.createClient(port, host);
    }

    public List<Post> findPosts(String query, Strategy strategy, int limit) {
        if (strategy == Strategy.FUZZY) {
            return new FuzzyMatcher(transportClient).findPosts(query, limit);
        } else if (strategy == Strategy.EXACT_MATCH) {
            return new StringMatcher(transportClient).findPosts(query, limit);
        }
        throw new RuntimeException("invalid strategy");
    }

    public void close() {
        transportClient.close();
    }

    public void deleteIndex() {
    }

    public static void main(String[] args) {
        @Cleanup ElasticClient c = new ElasticClient();
    }

}
