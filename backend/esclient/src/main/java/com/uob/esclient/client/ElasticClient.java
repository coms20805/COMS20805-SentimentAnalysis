package com.uob.esclient.client;

import com.uob.esclient.factory.ClientFactory;
import com.uob.esclient.search.FuzzyMatcher;
import com.uob.esclient.search.GreedyMatcher;
import com.uob.esclient.search.SearchQuery;
import com.uob.esclient.search.StringMatcher;

import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
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

    public <P> List<P> findPosts(SearchQuery sq, Class<P> postClazz) {
        switch (sq.strategy) {
            case FUZZY_MATCH:
                return new FuzzyMatcher(transportClient).findPosts(sq, postClazz);
            case EXACT_MATCH:
                return new StringMatcher(transportClient).findPosts(sq, postClazz);
            case GREEDY_MATCH:
                return new GreedyMatcher(transportClient).findPosts(sq, postClazz);
        }
        throw new RuntimeException("strategy not found", null);
    }

    public void close() {
        transportClient.close();
    }

    public void deleteAllDocsIn(String index) {
        transportClient.admin().
                indices().
                delete(new DeleteIndexRequest(index)).
                actionGet();
    }

    //TODO
    public void deleteDoc(String index, int id) {

    }
}
