package com.uob.esclient;

import com.uob.esclient.factory.ClientFactory;
import com.uob.esclient.post.Post;
import com.uob.esclient.search.FuzzyMatcher;
import com.uob.esclient.search.Strategy;
import com.uob.esclient.search.StringMatcher;

import org.elasticsearch.client.transport.TransportClient;

import java.net.UnknownHostException;
import java.util.List;


public final class ElasticClient {
    private final TransportClient transportClient;

    public ElasticClient() {
        this.transportClient = ClientFactory.createClient();
    }

    public ElasticClient(int port, String host) throws UnknownHostException {
        this.transportClient = ClientFactory.createClient(port, host);
    }


    public <P> List<P> findPosts(String searchQuery, Strategy strategy, int limit, Class<P> postClazz) {
        switch (strategy) {
            case FUZZY:
                return new FuzzyMatcher(transportClient).findPosts(searchQuery, limit, postClazz);
            case EXACT_MATCH:
                return new StringMatcher(transportClient).findPosts(searchQuery, limit, postClazz);
        }
        throw new RuntimeException("invalid strategy");
    }

    public <P> List<P> findPosts(String searchQuery, Strategy strategy, Class<P> postClazz) {
        return null;
    }

    public void close() {
        transportClient.close();
    }

    public void deleteIndex() {
    }


}
