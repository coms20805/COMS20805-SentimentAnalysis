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


    //keeps as placeholder for now, will remove later and use the generified method below instead
    public List<Post> findPosts(String searchQuery, Strategy strategy, int limit) {
        if (strategy == Strategy.FUZZY) {
            return new FuzzyMatcher(transportClient).findPosts(searchQuery, limit);
        } else if (strategy == Strategy.EXACT_MATCH) {
            return new StringMatcher(transportClient).findPosts(searchQuery, limit);
        }
        throw new RuntimeException("invalid strategy");
    }

    public <P> List<P> findPosts(String searchQuery, Strategy strategy, int limit, Class<P> postClazz) {
        return null;
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
