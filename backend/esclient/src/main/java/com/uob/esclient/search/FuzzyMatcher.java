package com.uob.esclient.search;

import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.MultiMatchQueryBuilder;

import static org.elasticsearch.index.query.QueryBuilders.boolQuery;
import static org.elasticsearch.index.query.QueryBuilders.multiMatchQuery;

/*
This Matcher attempts to finds documents that match the literal
query "approximately".

Eg: literal query ->  "python"
document_1 -> {"pythn is ...".}

A fuzzy matcher will match this document
against the literal query, despite the typo in the document.

See more here: https://en.wikipedia.org/wiki/Approximate_string_matching
 */
public final class FuzzyMatcher extends Matcher {

    public FuzzyMatcher(TransportClient client) {
        super(client);
    }


    @Override
    SearchRequestBuilder buildSearchRequest(String literalQuery, String fieldToCmpAgainst) {
        MultiMatchQueryBuilder fuzzyMmQueryBuilder = multiMatchQuery(
                literalQuery, fieldToCmpAgainst).fuzziness("AUTO");

        BoolQueryBuilder b = boolQuery().should(fuzzyMmQueryBuilder);
        return client.prepareSearch().setQuery(b);
    }
}
