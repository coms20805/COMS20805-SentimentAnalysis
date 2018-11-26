package com.uob.esclient.search;

import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.MultiMatchQueryBuilder;

import static org.elasticsearch.index.query.QueryBuilders.boolQuery;
import static org.elasticsearch.index.query.QueryBuilders.multiMatchQuery;

public final class FuzzyMatcher extends Matcher {

    public FuzzyMatcher(TransportClient client) {
        super(client);
    }

    @Override
    SearchRequestBuilder buildSearchRequest(String query, String fieldToCmpAgainst) {
        MultiMatchQueryBuilder fuzzyMmQueryBuilder = multiMatchQuery(
                query, fieldToCmpAgainst).fuzziness("AUTO");

        BoolQueryBuilder b = boolQuery().should(fuzzyMmQueryBuilder);
        return client.prepareSearch().setQuery(b);
    }

}
