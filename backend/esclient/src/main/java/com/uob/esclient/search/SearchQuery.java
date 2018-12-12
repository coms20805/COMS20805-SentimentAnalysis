package com.uob.esclient.search;

import java.util.Objects;
import java.util.function.Consumer;


public class SearchQuery {
    public final String literalQuery;
    public final int limit;
    public final String fieldToCompareAgainst;
    public final Strategy strategy;

    private SearchQuery(Builder builder) {
        this.literalQuery = Objects.requireNonNull(builder.literalQuery,
                NonNullErrorTemplate("query"));

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
        public String literalQuery;
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
