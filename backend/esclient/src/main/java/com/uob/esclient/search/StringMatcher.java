package com.uob.esclient.search;

import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;

public final class StringMatcher extends Matcher {


    public StringMatcher(TransportClient client) {
        super(client);
    }

    @Override
    SearchRequestBuilder buildSearchRequest(String literalQuery, String fieldToCmpAgainst) {
        MatchQueryBuilder content = QueryBuilders.matchQuery(fieldToCmpAgainst, literalQuery);
        return client.prepareSearch().setQuery(content);
    }
}
