package com.uob.esclient.search;

import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.index.query.MatchAllQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;


//This Matcher attempts to match a query against ALL posts in the elastic search database. Can be used to test/sanity-check
//if the database is populated.
public final class GreedyMatcher extends Matcher {
    public GreedyMatcher(TransportClient client) {
        super(client);
    }

    @Override
    SearchRequestBuilder buildSearchRequest(String literalQuery, String fieldToCmpAgainst) {
        MatchAllQueryBuilder srb = QueryBuilders.matchAllQuery();
        return client.prepareSearch().setQuery(srb);

    }
}
