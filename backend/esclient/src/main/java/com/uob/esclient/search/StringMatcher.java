package com.uob.esclient.search;

import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;

public class StringMatcher extends Matcher {


    public StringMatcher(TransportClient client) {
        super(client);
    }

    @Override
    SearchRequestBuilder queryBuilder(String query) {
        MatchQueryBuilder content = QueryBuilders.matchQuery("content", query);
        return client.prepareSearch().setQuery(content);
    }
}
