package com.uob.esclient.post;

import java.util.Date;

import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class Post {
    private final String content;
    private final Date timestamp;
    private final String url;
    private final double score;
}
