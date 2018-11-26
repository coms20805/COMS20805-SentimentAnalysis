package com.uob.esclient.client;

import com.uob.esclient.factory.ClientFactory;
import com.uob.esclient.post.Post;
import com.uob.esclient.search.FuzzyMatcher;
import com.uob.esclient.search.GreedyMatcher;
import com.uob.esclient.search.SearchQuery;
import com.uob.esclient.search.Strategy;
import com.uob.esclient.search.StringMatcher;

import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.search.SearchRequestBuilder;
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

    public List<?> findPosts(SearchQuery sq) {
        switch (sq.strategy) {
            case FUZZY_MATCH:
                return new FuzzyMatcher(transportClient).findPosts(sq);
            case EXACT_MATCH:
                return new StringMatcher(transportClient).findPosts(sq);
            case GREEDY_MATCH:
                return new GreedyMatcher(transportClient).findPosts(sq);
        }
        throw new RuntimeException("strategy not found", null);
    }

    public void close() {
        transportClient.close();
    }

    private void deleteAllDocsIn(String index) {
        transportClient.admin().
                indices().
                delete(new DeleteIndexRequest(index)).
                actionGet();
    }

    //TODO
    private void deleteDoc(String index, int id) {

    }
}
