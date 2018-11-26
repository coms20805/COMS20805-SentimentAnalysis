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
        this.query = Objects.requireNonNull(builder.query,
                NonNullErrorTemplate("query"));

        this.postClazz = Objects.requireNonNull(builder.postClazz,
                NonNullErrorTemplate("post class"));

        this.limit = builder.limit;

        this.fieldToCompareAgainst = Objects.requireNonNull(builder.fieldToCompareAgainst,
                NonNullErrorTemplate("fieldToCmpAgainst"));

        this.strategy = Objects.requireNonNull(builder.strategy,
                NonNullErrorTemplate("strategy"));
    }

    public static Builder builder() {
        return new Builder();
    }

    private static String NonNullErrorTemplate(String field) {
        return field + " cannot be null";
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
