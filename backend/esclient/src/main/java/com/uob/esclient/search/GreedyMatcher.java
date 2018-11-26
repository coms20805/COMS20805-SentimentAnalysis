package com.uob.esclient.search;

import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.index.query.MatchAllQueryBuilder;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;


//matches against ALL posts
public final class GreedyMatcher extends Matcher {
    public GreedyMatcher(TransportClient client) {
        super(client);
    }

    @Override
    SearchRequestBuilder buildSearchRequest(String query, String fieldToCmpAgainst) {
        MatchAllQueryBuilder srb = QueryBuilders.matchAllQuery();
        return client.prepareSearch().setQuery(srb);

    }
}
