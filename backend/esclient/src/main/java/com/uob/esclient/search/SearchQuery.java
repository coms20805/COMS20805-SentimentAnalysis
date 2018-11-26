package com.uob.esclient.search;

import java.util.Objects;
import java.util.function.Consumer;


public class SearchQuery {
    public final String query;
    public final Class<?> postClazz;
    public final int limit;
    public final String fieldToCompareAgainst;
    public final Strategy strategy;

    private SearchQuery(Builder builder) {
        this.query = Objects.requireNonNull(builder.query, "query cannot be null");
        this.postClazz = Objects.requireNonNull(builder.postClazz, "post class cannot be null");
        this.limit = builder.limit;
        this.fieldToCompareAgainst = Objects.requireNonNull(builder.fieldToCompareAgainst, "fieldToCmp cannot be null");
        this.strategy = Objects.requireNonNull(builder.strategy, "strategy cannot be null");

    }

    public static Builder builder() {
        return new Builder();
    }


    public static class Builder {
        public String query;
        public Class<?> postClazz;
        public int limit;
        public String fieldToCompareAgainst;
        public Strategy strategy;

        public Builder with(Consumer<Builder> func) {
            func.accept(this);
            return this;
        }

        public SearchQuery build() {
            return new SearchQuery(this);
        }
    }
}
